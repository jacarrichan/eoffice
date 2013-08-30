package com.cyjt.oa.action.personal;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.jbpm.pv.ParamField;
import com.cyjt.core.util.BeanUtil;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.action.flow.FlowRunInfo;
import com.cyjt.oa.action.flow.ProcessActivityAssistant;
import com.cyjt.oa.model.flow.ProDefinition;
import com.cyjt.oa.model.flow.ProcessForm;
import com.cyjt.oa.model.flow.ProcessRun;
import com.cyjt.oa.model.personal.ErrandsRegister;
import com.cyjt.oa.service.flow.JbpmService;
import com.cyjt.oa.service.flow.ProcessRunService;
import com.cyjt.oa.service.personal.ErrandsRegisterService;

import flexjson.JSONSerializer;

public class ErrandsRegisterAction extends BaseAction {

	@Resource
	private ProcessRunService processRunService;

	@Resource
	private JbpmService jbpmService;

	@Resource
	private ErrandsRegisterService errandsRegisterService;
	private ErrandsRegister errandsRegister;
	private Long dateId;

	public Long getDateId() {
		return this.dateId;
	}

	public void setDateId(Long dateId) {
		this.dateId = dateId;
	}

	public ErrandsRegister getErrandsRegister() {
		return this.errandsRegister;
	}

	public void setErrandsRegister(ErrandsRegister errandsRegister) {
		this.errandsRegister = errandsRegister;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		List<ErrandsRegister> list = this.errandsRegisterService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] {
				"startTime", "endTime" });
		buff.append(serializer.serialize(list));

		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.errandsRegisterService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ErrandsRegister errandsRegister = this.errandsRegisterService
				.get(this.dateId);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(errandsRegister));
		sb.append("}");
		this.setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String save() {
		if (this.errandsRegister.getDateId() != null) {
			ErrandsRegister org = this.errandsRegisterService
					.get(this.errandsRegister.getDateId());
			try {
				BeanUtil.copyNotNullProperties(org, this.errandsRegister);
				this.errandsRegisterService.save(org);
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
		} else {
			this.errandsRegister.setAppUser(ContextUtil.getCurrentUser());
			this.errandsRegister.setStatus(Short.valueOf((short) 0));
			this.errandsRegisterService.save(this.errandsRegister);

			Map<String, ParamField> fieldMap = this
					.constructStartFlowMap(this.errandsRegister);

			ProDefinition proDefintion = this.jbpmService
					.getProDefinitionByKey("ReqHolidayOut");

			if (proDefintion != null) {
				ProcessRun processRun = this.processRunService
						.initNewProcessRun(proDefintion);

				ProcessForm processForm = new ProcessForm();
				processForm.setActivityName("开始");
				processForm.setProcessRun(processRun);

				FlowRunInfo runInfo = new FlowRunInfo();
				runInfo.setParamFields(fieldMap);
				runInfo.setStartFlow(true);

				runInfo.setdAssignId(this.errandsRegister.getApprovalId()
						.toString());
				// 已经在ProcessActivityAction保存了此信息，故干掉 2012/9/5
				this.processRunService.saveProcessRun(processRun, processForm,
						runInfo);
			} else {
				this.logger.error("请假流程没有定义！");
			}
		}

		this.setJsonString("{success:true}");
		return "success";
	}

	private Map<String, ParamField> constructStartFlowMap(
			ErrandsRegister register) {
		String activityName = "开始";
		String processName = "请假外出";

		Map<String, ParamField> map = ProcessActivityAssistant
				.constructFieldMap(processName, activityName);

		ParamField pfDateId = map.get("dateId");

		if (pfDateId != null) {
			pfDateId.setValue(register.getDateId().toString());
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParamField pfOption = map.get("reqDesc");
		if (pfOption != null) {
			pfOption.setValue(register.getDescp());
		}

		ParamField pfStartTime = map.get("startTime");
		if (pfStartTime != null) {
			pfStartTime.setValue(sdf.format(register.getStartTime()));
		}

		ParamField pfEndTime = map.get("endTime");
		if (pfEndTime != null) {
			pfEndTime.setValue(sdf.format(register.getEndTime()));
		}

		ParamField pfApprovalName = map.get("approvalName");
		if (pfApprovalName != null) {
			pfApprovalName.setValue(register.getApprovalName());
		}

		return map;
	}
}
