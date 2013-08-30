package com.cyjt.oa.action.hrm;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.hrm.EmpProfile;
import com.cyjt.oa.model.hrm.JobChange;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.hrm.EmpProfileService;
import com.cyjt.oa.service.hrm.JobChangeService;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class JobChangeAction extends BaseAction {

	@Resource
	private JobChangeService jobChangeService;
	private JobChange jobChange;

	@Resource
	private EmpProfileService empProfileService;
	private Long changeId;

	public Long getChangeId() {
		return this.changeId;
	}

	public void setChangeId(Long changeId) {
		this.changeId = changeId;
	}

	public JobChange getJobChange() {
		return this.jobChange;
	}

	public void setJobChange(JobChange jobChange) {
		this.jobChange = jobChange;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<JobChange> list = this.jobChangeService.getAll(filter);

		Type type = new TypeToken<List<JobChange>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.jobChangeService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		JobChange jobChange = this.jobChangeService.get(this.changeId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:[");
		sb.append(gson.toJson(jobChange));
		sb.append("]}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.jobChange.setRegName(ContextUtil.getCurrentUser().getFullname());
		this.jobChange.setRegTime(new Date());
		this.jobChangeService.save(this.jobChange);
		setJsonString("{success:true}");
		return "success";
	}

	public String load() {
		String strId = getRequest().getParameter("changeId");
		if (StringUtils.isNotEmpty(strId)) {
			this.jobChange = (this.jobChangeService.get(new Long(strId)));
		}
		return "load";
	}

	public String check() {
		AppUser appUser = ContextUtil.getCurrentUser();
		Short status = this.jobChange.getStatus();
		String changeOpinion = this.jobChange.getCheckOpinion();
		Long changeId = this.jobChange.getChangeId();
		if (changeId != null) {
			this.jobChange = (this.jobChangeService.get(changeId));
			this.jobChange.setStatus(status);
			this.jobChange.setCheckOpinion(changeOpinion);
			this.jobChange.setCheckName(appUser.getFullname());
			this.jobChange.setCheckTime(new Date());
			this.jobChangeService.save(this.jobChange);
			if (status.shortValue() == 1) {
				Long profileId = this.jobChange.getProfileId();
				if (profileId != null) {
					EmpProfile empProfile = this.empProfileService.get(profileId);
					empProfile.setJobId(this.jobChange.getNewJobId());
					empProfile.setPosition(this.jobChange.getNewJobName());
					empProfile.setDepId(this.jobChange.getNewDepId());
					empProfile.setDepName(this.jobChange.getNewDepName());
					empProfile.setStandardId(this.jobChange.getNewStandardId());
					empProfile.setStandardMiNo(this.jobChange.getNewStandardNo());
					empProfile.setStandardName(this.jobChange.getNewStandardName());
					empProfile.setStandardMoney(this.jobChange.getNewTotalMoney());
					this.empProfileService.merge(empProfile);
				}
			}
			setJsonString("{success:true}");
		} else {
			setJsonString("{success:false}");
		}
		return "success";
	}
}
