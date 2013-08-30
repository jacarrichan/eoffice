package com.cyjt.oa.action.mobile.flow;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.model.Transition;

import org.jbpm.pvm.internal.task.TaskImpl;

import com.cyjt.core.jbpm.pv.ParamField;
import com.cyjt.core.jbpm.pv.TaskInfo;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.action.flow.FlowRunInfo;
import com.cyjt.oa.action.flow.ProcessActivityAssistant;
import com.cyjt.oa.model.flow.ProDefinition;
import com.cyjt.oa.model.flow.ProcessForm;
import com.cyjt.oa.model.flow.ProcessRun;
import com.cyjt.oa.model.flow.Transform;
import com.cyjt.oa.service.flow.JbpmService;
import com.cyjt.oa.service.flow.ProDefinitionService;
import com.cyjt.oa.service.flow.ProcessRunService;
import com.cyjt.oa.service.flow.TaskService;

public class MobileTaskAction extends BaseAction {

	@Resource
	private ProcessRunService processRunService;

	@Resource
	private ProDefinitionService proDefinitionService;
	private Long defId;
	private String taskId;
	private String processName;
	private String taskName;
	private List<Transform> outTrans = new ArrayList<Transform>();
	private String vmPath;

	@Resource(name = "flowTaskService")
	private TaskService flowTaskService;

	@Resource
	private JbpmService jbpmService;

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getVmPath() {
		return this.vmPath;
	}

	public void setVmPath(String vmPath) {
		this.vmPath = vmPath;
	}

	public List<Transform> getOutTrans() {
		return this.outTrans;
	}

	public void setOutTrans(List<Transform> outTrans) {
		this.outTrans = outTrans;
	}

	@Override
	public String list() {
		PagingBean pb = this.getInitPagingBean();
		List<TaskInfo> tasks = this.flowTaskService.getTaskInfosByUserId(
				ContextUtil.getCurrentUserId().toString(), pb, null);
		this.getRequest().setAttribute("taskList", tasks);
		return "success";
	}

	public String next() {
		this.taskId = this.getRequest().getParameter("taskId");

		if (StringUtils.isNotEmpty(this.taskId)) {
			TaskImpl task = (TaskImpl) this.jbpmService
					.getTaskById(this.taskId);
			this.taskName = task.getName();

			ProcessDefinition pd = this.jbpmService
					.getProcessDefinitionByTaskId(this.taskId);
			ProDefinition systemProDef = this.proDefinitionService
					.getByDeployId(pd.getDeploymentId());
			this.processName = systemProDef.getName();

			this.vmPath = (this.processName + "/" + this.taskName);

			String viewPath = this.getSession().getServletContext()
					.getRealPath("")
					+ "/mobile/flow/FlowForm/" + this.vmPath + ".vm";
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("viewPath:" + viewPath);
			}

			if (!new File(viewPath).exists()) {
				this.vmPath = "通用/表单";
			}

			List<Transition> trans = this.jbpmService
					.getTransitionsByTaskId(this.taskId.toString());
			for (Transition tran : trans) {
				if (tran.getDestination() != null) {
					this.outTrans.add(new Transform(tran));
				}
			}
		}

		return "next";
	}

	public String saveNext() {
		String signalName = this.getRequest().getParameter("signalName");
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("taskId:" + this.taskId + " signalName:"
					+ signalName + " taskName:" + this.taskName);
		}

		FlowRunInfo flowRunInfo = this.getFlowRunInfo();
		this.processRunService.saveAndNextStep(flowRunInfo);

		return "list";
	}

	public String start() {
		ProDefinition systemProDef = this.proDefinitionService.get(this.defId);
		this.taskName = this.jbpmService.getStartNodeName(systemProDef);
		this.processName = systemProDef.getName();

		this.vmPath = (this.processName + "/" + this.taskName);

		String viewPath = this.getSession().getServletContext().getRealPath("")
				+ "/mobile/flow/FlowForm/" + this.vmPath + ".vm";
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("viewPath:" + viewPath);
		}

		if (!new File(viewPath).exists()) {
			this.vmPath = "通用/开始";
		}

		return "start";
	}

	public String saveStart() {
		FlowRunInfo flowRunInfo = this.getFlowRunInfo();
		ProcessRun processRun = this.initNewProcessRun();
		ProcessForm processForm = this.initNewProcessForm(processRun);
		this.processRunService.saveProcessRun(processRun, processForm,
				flowRunInfo);
		return "list";
	}

	private Map<String, ParamField> constructFieldMap() {
		HttpServletRequest request = this.getRequest();

		if (StringUtils.isEmpty(this.taskName)) {
			ProDefinition systemProDef = null;
			if (StringUtils.isNotEmpty(this.taskId)) {
				ProcessDefinition pd = this.jbpmService
						.getProcessDefinitionByTaskId(this.taskId);
				systemProDef = this.proDefinitionService.getByDeployId(pd
						.getDeploymentId());
			} else {
				systemProDef = this.proDefinitionService.get(this.defId);
			}
			this.taskName = this.jbpmService.getStartNodeName(systemProDef);
			this.processName = systemProDef.getName();
		} else if ((StringUtils.isEmpty(this.processName))
				&& (StringUtils.isNotEmpty(this.taskId))) {
			ProcessDefinition pd = this.jbpmService
					.getProcessDefinitionByTaskId(this.taskId);
			ProDefinition systemProDef = this.proDefinitionService
					.getByDeployId(pd.getDeploymentId());
			this.processName = systemProDef.getName();
		}

		Map<String, ParamField> map = ProcessActivityAssistant
				.constructMobileFieldMap(this.processName, this.taskName);

		Iterator<String> fieldNames = map.keySet().iterator();
		while (fieldNames.hasNext()) {
			String name = (String) fieldNames.next();
			ParamField pf = (ParamField) map.get(name);

			pf.setName(pf.getName().replace(".", "_"));
			pf.setValue(request.getParameter(name));
		}
		return map;
	}

	private ProcessRun initNewProcessRun() {
		ProDefinition proDefinition = this.proDefinitionService.get(this.defId);
		return this.processRunService.initNewProcessRun(proDefinition);
	}

	private ProcessForm initNewProcessForm(ProcessRun processRun) {
		ProcessForm processForm = new ProcessForm();
		processForm.setActivityName(this.taskName);
		processForm.setProcessRun(processRun);
		return processForm;
	}

	private FlowRunInfo getFlowRunInfo() {
		FlowRunInfo info = new FlowRunInfo(this.getRequest());
		Map<String, ParamField> fieldMap = this.constructFieldMap();
		info.setParamFields(fieldMap);
		return info;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Long getDefId() {
		return this.defId;
	}

	public void setDefId(Long defId) {
		this.defId = defId;
	}
}
