package com.palmelf.eoffice.dao.admin.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.admin.ConfPrivilegeDao;
import com.palmelf.eoffice.dao.admin.ConferenceDao;
import com.palmelf.eoffice.dao.system.AppUserDao;
import com.palmelf.eoffice.dao.system.FileAttachDao;
import com.palmelf.eoffice.model.admin.ConfPrivilege;
import com.palmelf.eoffice.model.admin.Conference;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.system.FileAttach;

public class ConferenceDaoImpl extends BaseDaoImpl<Conference> implements ConferenceDao {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Resource
	private FileAttachDao fileAttachDao;

	@Resource
	private AppUserDao appUserDao;

	@Resource
	private ConfPrivilegeDao confPrivilegeDao;

	public ConferenceDaoImpl() {
		super(Conference.class);
	}

	public List<Conference> getConfTopic(String confTopic, PagingBean pb) {
		Long userId = ContextUtil.getCurrentUserId();
		ArrayList paramList = new ArrayList();
		StringBuffer bf = new StringBuffer("select c from Conference c,ConfPrivilege p where c.confId=p.confId ");
		bf.append("and p.rights=3 and p.userId=" + userId);
		if ((confTopic != null) && (!confTopic.isEmpty())) {
			bf.append(" and c.confTopic like ? ");
			paramList.add("%" + confTopic + "%");
		}
		this.logger.debug("可创建会议纪要的HQL：" + bf.toString());
		return this.findByHql(bf.toString(), paramList.toArray(), pb);
	}

	public String baseUserIdSearchFullName(String userIds) {
		String fullNames = "";
		for (String userId : userIds.split(",")) {
			fullNames = fullNames + (this.appUserDao.get(new Long(userId))).getFullname() + ",";
		}
		return fullNames.substring(0, fullNames.length() - 1);
	}

	public Conference send(Conference conference, String view, String updater, String summary, String fileIds) {
		if (conference.getIsEmail() != null) {
			conference.getIsEmail().shortValue();
		}

		if (conference.getIsMobile() != null) {
			conference.getIsMobile().shortValue();
		}

		return this.temp(conference, view, updater, summary, fileIds);
	}

	public Conference temp(Conference conference, String view, String updater, String summary, String fileIds) {
		if ((fileIds != null) && (!fileIds.isEmpty())) {
			Set<FileAttach> set = new HashSet<FileAttach>();
			for (String f : fileIds.split(",")) {
				FileAttach fa = this.fileAttachDao.get(new Long(f));
				set.add(fa);
			}
			conference.setFileAttachs(set);
		}
		Conference cf = super.save(conference);

		Set sett = new HashSet();

		this.setConfPrivilege(cf.getConfId(), view, 1, sett);

		this.setConfPrivilege(cf.getConfId(), updater, 2, sett);

		this.setConfPrivilege(cf.getConfId(), summary, 3, sett);

		this.confPrivilegeDao.delete(cf.getConfId());

		cf.setConfPrivileges(sett);
		return super.save(cf);
	}

	public String judgeBoardRoomNotUse(Date startTime, Date endTime, Long roomId) {
		ArrayList<Object> paramList = new ArrayList<Object>();
		String msg = "success";
		String hql = "select t from Conference t where t.roomId = ? and t.status=1 and ";
		hql = hql
				+ "t.startTime <= to_date(?,'yyyy-mm-dd hh24:mi:ss') and t.endTime >= to_date(?,'yyyy-mm-dd hh24:mi:ss') ";
		paramList.add(roomId);
		paramList.add(ConferenceDaoImpl.sdf.format(startTime));
		paramList.add(ConferenceDaoImpl.sdf.format(endTime));
		List<Conference> list = this.findByHql(hql, paramList.toArray());
		if ((list != null) && (list.size() > 0)) {
			Conference conference = list.get(0);
			msg = conference.getRoomName() + "，在" + ConferenceDaoImpl.sdf.format(conference.getStartTime()) + " 至 "
					+ ConferenceDaoImpl.sdf.format(conference.getEndTime()) + "这段时间不可使用，请选择其他时间段！";
		} else {
			msg = "success";
		}
		this.logger.debug("Conference中判断会议室是否可用：" + hql);
		return msg;
	}

	public String apply(Long confId, String checkReason, boolean bo) {
		int status = bo ? 1 : 3;
		Conference conference = this.get(confId);
		conference.setStatus(Short.valueOf((short) status));
		conference.setCheckReason(checkReason);
		return "success";
	}

	private void setConfPrivilege(Long confId, String ids, int type, Set<ConfPrivilege> set) {
		for (String id : ids.split(",")) {
			AppUser appUser = this.appUserDao.get(new Long(id));
			ConfPrivilege cp = new ConfPrivilege();
			cp.setConfId(confId);
			cp.setUserId(appUser.getUserId());
			cp.setFullname(appUser.getFullname());
			cp.setRights(Short.valueOf((short) type));
			set.add(cp);
		}
	}
}
