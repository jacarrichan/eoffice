package com.cyjt.oa.action.flow;

import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.flow.ProcessRun;
import com.cyjt.oa.service.flow.JbpmService;
import com.cyjt.oa.service.flow.ProcessFormService;
import com.cyjt.oa.service.flow.ProcessRunService;
import java.util.List;
import javax.annotation.Resource;
import org.jbpm.api.ProcessInstance;

public class ProcessRunDetailAction extends BaseAction {

	@Resource
	private ProcessRunService processRunService;

	@Resource
	private ProcessFormService processFormService;

	@Resource
	private JbpmService jbpmService;
	private Long runId;
	private Long taskId;

	public Long getRunId() {
		return this.runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@Override
	public String execute() throws Exception {
		ProcessRun processRun = null;
		if (this.runId == null) {
			ProcessInstance pis = this.jbpmService
					.getProcessInstanceByTaskId(this.taskId.toString());
			processRun = this.processRunService.getByPiId(pis.getId());
			getRequest().setAttribute("processRun", processRun);
			this.runId = processRun.getRunId();
		} else {
			processRun = this.processRunService.get(this.runId);
		}
		List<?> pfList = this.processFormService.getByRunId(this.runId);

		getRequest().setAttribute("pfList", pfList);

		return "success";
	}
}
