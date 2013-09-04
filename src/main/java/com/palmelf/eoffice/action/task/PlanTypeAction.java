package com.palmelf.eoffice.action.task;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.task.PlanType;
import com.palmelf.eoffice.model.task.WorkPlan;
import com.palmelf.eoffice.service.task.PlanTypeService;
import com.palmelf.eoffice.service.task.WorkPlanService;

public class PlanTypeAction extends BaseAction {

	@Resource
	private PlanTypeService planTypeService;
	private PlanType planType;

	@Resource
	private WorkPlanService workPlanService;
	private Long typeId;

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public PlanType getPlanType() {
		return this.planType;
	}

	public void setPlanType(PlanType planType) {
		this.planType = planType;
	}

	public String combo() {
		StringBuffer sb = new StringBuffer();

		List<PlanType> planTypeList = this.planTypeService.getAll();
		sb.append("[");
		for (PlanType planType : planTypeList) {
			sb.append("['").append(planType.getTypeId()).append("','")
					.append(planType.getTypeName()).append("'],");
		}
		if (planTypeList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		this.setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<PlanType> list = this.planTypeService.getAll(filter);

		Type type = new TypeToken<List<PlanType>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				QueryFilter filter = new QueryFilter(this.getRequest());
				filter.addFilter("Q_planType.typeId_L_EQ", id);
				List<WorkPlan> list = this.workPlanService.getAll(filter);
				if (list.size() > 0) {
					this.jsonString = "{success:false,message:'类型下还有计划，请移走该类型的计划任务后，再删除类型！'}";
					return "success";
				}
				this.planTypeService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		PlanType planType = this.planTypeService.get(this.typeId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(planType));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.planTypeService.save(this.planType);
		this.setJsonString("{success:true}");
		return "success";
	}
}
