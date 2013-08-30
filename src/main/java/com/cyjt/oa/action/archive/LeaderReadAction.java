package com.cyjt.oa.action.archive;

import com.google.gson.Gson;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.archive.Archives;
import com.cyjt.oa.model.archive.LeaderRead;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.archive.ArchivesService;
import com.cyjt.oa.service.archive.LeaderReadService;
import com.cyjt.oa.service.system.AppUserService;
import flexjson.JSONSerializer;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class LeaderReadAction extends BaseAction {

	@Resource
	private LeaderReadService leaderReadService;
	private LeaderRead leaderRead;

	@Resource
	private ArchivesService archivesService;

	@Resource
	private AppUserService appUserService;
	private Long readId;
	private String leaderOpinion;
	private Short isPass;
	private String checkName;
	private Archives archives;
	private Integer depLevel;
	private Long flowAssignId;

	public Integer getDepLevel() {
		return this.depLevel;
	}

	public void setDepLevel(Integer depLevel) {
		this.depLevel = depLevel;
	}

	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	public Short getIsPass() {
		return this.isPass;
	}

	public void setIsPass(Short isPass) {
		this.isPass = isPass;
	}

	public String getLeaderOpinion() {
		return this.leaderOpinion;
	}

	public void setLeaderOpinion(String leaderOpinion) {
		this.leaderOpinion = leaderOpinion;
	}

	public Long getReadId() {
		return this.readId;
	}

	public void setReadId(Long readId) {
		this.readId = readId;
	}

	public LeaderRead getLeaderRead() {
		return this.leaderRead;
	}

	public void setLeaderRead(LeaderRead leaderRead) {
		this.leaderRead = leaderRead;
	}

	public String getCheckName() {
		return this.checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		filter.addFilter("Q_archives.archType_SN_EQ",
				Archives.ARCHIVE_TYPE_RECEIVE.toString());
		List list = this.leaderReadService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] {
				"createtime", "archives.issueDate", "archives.createtime" });
		buff.append(serializer.exclude(new String[] { "class" })
				.serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.leaderReadService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		LeaderRead leaderRead = this.leaderReadService.get(this.readId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(leaderRead));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String strArchivesId = getRequest().getParameter("archivesId");
		if (StringUtils.isNotEmpty(strArchivesId)) {
			LeaderRead leader = new LeaderRead();
			Archives archives = this.archivesService
					.get(new Long(strArchivesId));
			AppUser user = ContextUtil.getCurrentUser();
			leader.setArchives(archives);
			leader.setLeaderOpinion(this.leaderOpinion);
			leader.setIsPass(this.isPass);
			leader.setDepLevel(this.depLevel);
			leader.setUserId(user.getUserId());
			leader.setLeaderName(user.getFullname());
			leader.setCreatetime(new Date());
			leader.setCheckName(this.checkName);

			this.leaderReadService.save(leader);
			if ((this.depLevel != null) && (this.depLevel.intValue() == 1)) {
				String flowAssignId = getRequest().getParameter("flowAssignId");
				if (StringUtils.isNotEmpty(flowAssignId))
					archives.setStatus(Archives.STATUS_LEADERCHECK);
				else
					archives.setStatus(Archives.STATUS_DISPATCH);
			} else {
				archives.setStatus(Archives.STATUS_DISPATCH);
			}

			this.archivesService.save(archives);
		}
		setJsonString("{success:true}");
		return "success";
	}

	public String saveDep() {
		this.archives = (this.archivesService
				.get(this.archives.getArchivesId()));
		String archivesStatus = getRequest().getParameter("archivesStatus");
		if (StringUtils.isNotEmpty(archivesStatus)) {
			this.archives.setStatus(Short.valueOf(Short
					.parseShort(archivesStatus)));
		}
		this.archivesService.save(this.archives);

		this.leaderRead.setLeaderName(ContextUtil.getCurrentUser()
				.getFullname());
		this.leaderRead.setUserId(ContextUtil.getCurrentUserId());
		this.leaderRead.setArchives(this.archives);
		this.leaderRead.setCreatetime(new Date());
		this.leaderRead.setIsPass(LeaderRead.IS_PASS);
		this.leaderReadService.save(this.leaderRead);

		setJsonString("{success:true,readId:" + this.leaderRead.getReadId()
				+ "}");
		return "success";
	}
}
