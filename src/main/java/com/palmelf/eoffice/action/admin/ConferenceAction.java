package com.palmelf.eoffice.action.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.admin.BoardRoo;
import com.palmelf.eoffice.model.admin.BoardType;
import com.palmelf.eoffice.model.admin.Conference;
import com.palmelf.eoffice.service.admin.BoardRooService;
import com.palmelf.eoffice.service.admin.BoardTypeService;
import com.palmelf.eoffice.service.admin.ConferenceService;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

public class ConferenceAction extends BaseAction {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Resource
	private ConferenceService conferenceService;

	@Resource
	private BoardRooService boardRooService;

	@Resource
	private BoardTypeService boardTypeService;
	private Long confId;
	private String filePath;
	private String checkReason;
	private String viewer;
	private String updater;
	private String summary;
	private Conference conference;

	public String getViewer() {
		return this.viewer;
	}

	public void setViewer(String viewer) {
		this.viewer = viewer;
	}

	public String getUpdater() {
		return this.updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getConfId() {
		return this.confId;
	}

	public void setConfId(Long confId) {
		this.confId = confId;
	}

	public Conference getConference() {
		return this.conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	public String getCheckReason() {
		return this.checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public String displayMyconf() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_status_SN_EQ", "1");
		filter.addFilter("Q_startTime_D_GE", this.sdf.format(new Date()));
		filter.addSorted("startTime", "DESC");
		List<Conference> list = this.conferenceService.getAll(filter);
		for (int i = 0; i < list.size(); i++) {
			Conference cf = list.get(i);
			if ((containtMy(cf.getCompere())) && (containtMy(cf.getRecorder()))
					&& (containtMy(cf.getAttendUsers()))) {
				list.remove(i);
			}
			if (i > 7) {
				for (int j = 7; j < list.size(); j++)
					list.remove(j);
			}
		}
		getRequest().setAttribute("myConferenceList", list);
		return "display";
	}

	public String zanCun() {
		QueryFilter filter = new QueryFilter(getRequest());

		filter.addFilter("Q_status_SN_EQ", "0");
		return filter(filter);
	}

	public String myJoin() {
		return myJoinInfo(true);
	}

	public String myJoined() {
		return myJoinInfo(false);
	}

	public String daiKai() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_startTime_D_GE", this.sdf.format(new Date()));
		filter.addFilter("Q_status_SN_EQ", "1");
		return filter(filter);
	}

	public String yiKai() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_endTime_D_LE", this.sdf.format(new Date()));
		filter.addFilter("Q_status_SN_EQ", "1");
		return filter(filter);
	}

	public String getConfTopic() {
		PagingBean pb = getInitPagingBean();
		String confTopic = getRequest().getParameter("Q_confTopic_S_LK");
		List<Conference> list = this.conferenceService.getConfTopic(confTopic,
				pb);

		return toJson(pb, list);
	}

	public String send() {
		String msg = judgeBoardRoomNotUse();
		if (msg.equalsIgnoreCase("success")) {
			if (this.conference.getIsEmail() == null)
				this.conference.setIsEmail(Conference.ISNOEMAIL);
			if (this.conference.getIsMobile() == null)
				this.conference.setIsMobile(Conference.ISNOMOBILE);
			this.conference.setStatus(Conference.Apply);
			this.conferenceService.send(this.conference, this.viewer,
					this.updater, this.summary, this.filePath);
			setJsonString("{success:true}");
		} else {
			setJsonString("{failure:true,msg:'" + msg + "'}");
		}
		return "success";
	}

	public String temp() {
		String msg = judgeBoardRoomNotUse();
		if (msg.equalsIgnoreCase("success")) {
			if (this.conference.getIsEmail() == null)
				this.conference.setIsEmail(Conference.ISNOEMAIL);
			if (this.conference.getIsMobile() == null)
				this.conference.setIsMobile(Conference.ISNOMOBILE);
			this.conference.setStatus(Conference.TEMP);
			this.conferenceService.temp(this.conference, this.viewer,
					this.updater, this.summary, this.filePath);
			setJsonString("{success:true}");
		} else {
			setJsonString("{failure:true,msg:'" + msg + "'}");
		}
		return "success";
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());

		return filter(filter);
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids)
				this.conferenceService.remove(new Long(id));
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		Conference conference = this.conferenceService.get(this.confId);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(conference));
		setJsonString("}");
		return "success";
	}

	@Override
	public String save() {
		this.conferenceService.save(this.conference);
		setJsonString("{success:true}");
		return "success";
	}

	public String getBoardroo() {
		List<BoardRoo> list = this.boardRooService.getAll();
		StringBuffer bf = new StringBuffer("[");
		for (BoardRoo br : list) {
			bf.append("['").append(br.getRoomId()).append("','")
					.append(br.getRoomName()).append("'],");
		}
		bf.deleteCharAt(bf.length() - 1).append("]");
		setJsonString(bf.toString());
		return "success";
	}

	public String getTypeAll() {
		List<BoardType> list = this.boardTypeService.getAll();
		StringBuffer bf = new StringBuffer("[");
		for (BoardType bt : list) {
			bf.append("['").append(bt.getTypeId()).append("','")
					.append(bt.getTypeName()).append("'],");
		}
		bf.deleteCharAt(bf.length() - 1).append("]");
		setJsonString(bf.toString());
		return "success";
	}

	public String apply() {
		String status = getRequest().getParameter("status");
		boolean bo = (status != null) && (status.equals("1"));
		String msg = this.conferenceService.apply(this.confId,
				this.checkReason, bo);
		if (msg.equals("success"))
			setJsonString("{success:true}");
		else
			setJsonString("{failure:true,msg:'对不起，会议审核失败！'}");
		return "success";
	}

	public String daiConfApply() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_checkUserId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		filter.addFilter("Q_status_SN_EQ", "2");
		filter.addSorted("createtime", "DESC");
		return filter(filter);
	}

	public String unThrought() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_status_SN_EQ", "3");
		filter.addSorted("createtime", "DESC");
		return filter(filter);
	}

	public String displyApply() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_checkUserId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		filter.addFilter("Q_status_SN_EQ", "2");
		filter.addFilter("Q_startTime_D_GE", this.sdf.format(new Date()));
		filter.addSorted("createtime", "DESC");
		List<Conference> list = this.conferenceService.getAll(filter);
		if (list.size() > 8) {
			for (int i = 7; i < list.size(); i++) {
				list.remove(i);
			}
		}
		getRequest().setAttribute("applyConferenceList", list);
		return "displayApply";
	}

	private String toJson(PagingBean pb, List<Conference> list) {
		Type type = new TypeToken<List<Conference>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		setJsonString(buff.toString());
		return "success";
	}

	private String filter(QueryFilter filter) {
		filter.addSorted("startTime", "DESC");
		List<Conference> list = this.conferenceService.getAll(filter);

		Type type = new TypeToken<List<Conference>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		setJsonString(buff.toString());
		return "success";
	}

	private String myJoinInfo(boolean bo) {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_status_SN_EQ", "1");
		if (bo)
			filter.addFilter("Q_startTime_D_GE", this.sdf.format(new Date()));
		else
			filter.addFilter("Q_endTime_D_LE", this.sdf.format(new Date()));
		filter.addSorted("startTime", "DESC");
		List<Conference> list = this.conferenceService.getAll(filter);
		for (int i = 0; i < list.size(); i++) {
			Conference cf = list.get(i);
			if ((!containtMy(cf.getCompere()))
					|| (!containtMy(cf.getRecorder()))
					|| (!containtMy(cf.getAttendUsers())))
				continue;
			list.remove(i);
		}

		return filter(filter);
	}

	private boolean containtMy(String str) {
		boolean bo = true;
		Long userId = ContextUtil.getCurrentUserId();
		int index = str.indexOf(userId.toString());
		if ((index > 1) && (str.substring(index - 1, index).equals(",")))
			bo = false;
		else if (index == 0)
			bo = false;
		return bo;
	}

	private String judgeBoardRoomNotUse() {
		return this.conferenceService.judgeBoardRoomNotUse(
				this.conference.getStartTime(), this.conference.getEndTime(),
				this.conference.getRoomId());
	}
}
