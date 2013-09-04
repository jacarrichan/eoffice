package com.palmelf.eoffice.action.task;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.util.JsonUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.system.FileAttach;
import com.palmelf.eoffice.model.task.PlanAttend;
import com.palmelf.eoffice.model.task.WorkPlan;
import com.palmelf.eoffice.service.system.AppUserService;
import com.palmelf.eoffice.service.system.DepartmentService;
import com.palmelf.eoffice.service.system.FileAttachService;
import com.palmelf.eoffice.service.task.PlanAttendService;
import com.palmelf.eoffice.service.task.WorkPlanService;

import flexjson.JSONSerializer;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class WorkPlanAction extends BaseAction {

	@Resource
	private WorkPlanService workPlanService;
	private WorkPlan workPlan;

	@Resource
	private FileAttachService fileAttachService;

	@Resource
	private DepartmentService departmentService;

	@Resource
	private PlanAttendService planAttendService;

	@Resource
	private AppUserService appUserService;
	private Long planId;
	static short ISDEPARTMENT = 1;
	static short NOTDEPARTMENT = 0;
	static short ISPRIMARY = 1;
	static short NOTPRIMARY = 0;

	public Long getPlanId() {
		return this.planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public WorkPlan getWorkPlan() {
		return this.workPlan;
	}

	public void setWorkPlan(WorkPlan workPlan) {
		this.workPlan = workPlan;
	}

	@Override
	public String list() {
		Long userId = ContextUtil.getCurrentUserId();
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", userId.toString());
		List list = this.workPlanService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] {
				"startTime", "endTime" });
		buff.append(serializer.exclude(
				new String[] { "class", "appUser.password" }).serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String personal() {
		QueryFilter filter = new QueryFilter(getRequest());
		Long userId = ContextUtil.getCurrentUserId();
		filter.addFilter("Q_appUser.userId_L_EQ", userId.toString());
		filter.addFilter("Q_isPersonal_SN_EQ", "1");
		filter.addFilter("Q_status_SN_EQ", "1");
		List list = this.workPlanService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] {
				"startTime", "endTime" });
		buff.append(serializer.exclude(
				new String[] { "class", "appUser.password", "department" })
				.serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String department() {
		PagingBean pb = getInitPagingBean();
		AppUser user = ContextUtil.getCurrentUser();
		List list = this.workPlanService.findByDepartment(this.workPlan, user,
				pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] {
				"startTime", "endTime" });
		buff.append(serializer.exclude(
				new String[] { "class", "appUser.password", "department" })
				.serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.workPlanService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		WorkPlan workPlan = this.workPlanService.get(this.planId);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(workPlan));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String save() {
		String issueScopeIds = getRequest().getParameter("issueScopeIds");
		String participantsIds = getRequest().getParameter("participantsIds");
		String principalIds = getRequest().getParameter("principalIds");
		String fileIds = getRequest().getParameter("planFileIds");
		short isPersonal = this.workPlan.getIsPersonal().shortValue();
		this.workPlan.getPlanFiles().clear();
		if (StringUtils.isNotEmpty(fileIds)) {
			String[] fIds = fileIds.split(",");
			for (int i = 0; i < fIds.length; i++) {
				FileAttach fileAttach = this.fileAttachService.get(new Long(
						fIds[i]));
				this.workPlan.getPlanFiles().add(fileAttach);
			}
		}
		this.workPlan.setPlanFiles(this.workPlan.getPlanFiles());
		AppUser appUser = ContextUtil.getCurrentUser();
		this.workPlan.setAppUser(appUser);
		if (isPersonal == 1) {
			this.workPlan.setPrincipal(appUser.getFullname());
		}
		this.workPlanService.save(this.workPlan);
		if (isPersonal != 1) {
			if (StringUtils.isNotEmpty(issueScopeIds)) {
				boolean b = this.planAttendService.deletePlanAttend(
						this.workPlan.getPlanId(), Short.valueOf(ISDEPARTMENT),
						Short.valueOf(NOTPRIMARY));
				if (b) {
					String[] strIssueScopeId = issueScopeIds.split(",");
					for (int i = 0; i < strIssueScopeId.length; i++) {
						if (StringUtils.isNotEmpty(strIssueScopeId[i])) {
							Long depId = new Long(strIssueScopeId[i]);
							PlanAttend pa = new PlanAttend();
							pa.setDepartment(this.departmentService.get(depId));
							pa.setWorkPlan(this.workPlan);
							pa.setIsDep(Short.valueOf(ISDEPARTMENT));
							pa.setIsPrimary(Short.valueOf(NOTPRIMARY));
							this.planAttendService.save(pa);
						}
					}
				}
			}
			if (StringUtils.isNotEmpty(participantsIds)) {
				boolean b = this.planAttendService
						.deletePlanAttend(this.workPlan.getPlanId(),
								Short.valueOf(NOTDEPARTMENT),
								Short.valueOf(NOTPRIMARY));
				if (b) {
					String[] strParticipantsId = participantsIds.split(",");
					for (int i = 0; i < strParticipantsId.length; i++) {
						if (StringUtils.isNotEmpty(strParticipantsId[i])) {
							Long userId = new Long(strParticipantsId[i]);
							PlanAttend pa = new PlanAttend();
							pa.setAppUser(this.appUserService.get(userId));
							pa.setIsDep(Short.valueOf(NOTDEPARTMENT));
							pa.setWorkPlan(this.workPlan);
							pa.setIsPrimary(Short.valueOf(NOTPRIMARY));
							this.planAttendService.save(pa);
						}
					}
				}
			}
			if (StringUtils.isNotEmpty(principalIds)) {
				boolean b = this.planAttendService.deletePlanAttend(
						this.workPlan.getPlanId(),
						Short.valueOf(NOTDEPARTMENT), Short.valueOf(ISPRIMARY));
				if (b) {
					String[] strPrincipalId = principalIds.split(",");
					for (int i = 0; i < strPrincipalId.length; i++) {
						if (StringUtils.isNotEmpty(strPrincipalId[i])) {
							Long userId = new Long(strPrincipalId[i]);
							PlanAttend pa = new PlanAttend();
							pa.setAppUser(this.appUserService.get(userId));
							pa.setIsDep(Short.valueOf(NOTDEPARTMENT));
							pa.setWorkPlan(this.workPlan);
							pa.setIsPrimary(Short.valueOf(ISPRIMARY));
							this.planAttendService.save(pa);
						}
					}
				}
			}
		}
		setJsonString("{success:true}");
		return "success";
	}

	public String show() {
		String strPlanId = getRequest().getParameter("planId");
		if (StringUtils.isNotEmpty(strPlanId)) {
			Long planId = new Long(strPlanId);
			this.workPlan = (this.workPlanService.get(planId));
		}
		return "show";
	}

	public String display() {
		QueryFilter filter = new QueryFilter(getRequest());
		Long userId = ContextUtil.getCurrentUserId();
		filter.addFilter("Q_appUser.userId_L_EQ", userId.toString());
		filter.addFilter("Q_isPersonal_SN_EQ", "1");
		filter.addFilter("Q_status_SN_EQ", "1");
		filter.addSorted("planId", "desc");
		List list = this.workPlanService.getAll(filter);
		getRequest().setAttribute("planList", list);
		return "display";
	}

	public String displayDep() {
		PagingBean pb = new PagingBean(0, 8);
		AppUser user = ContextUtil.getCurrentUser();
		List list = this.workPlanService.findByDepartment(this.workPlan, user,
				pb);
		getRequest().setAttribute("planList", list);
		return "displayDep";
	}
}
