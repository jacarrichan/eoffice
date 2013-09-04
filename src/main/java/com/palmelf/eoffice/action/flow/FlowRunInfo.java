package com.palmelf.eoffice.action.flow;

import com.palmelf.core.jbpm.pv.ParamField;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class FlowRunInfo {
	private Map<String, Object> variables = new HashMap<String, Object>();

	private Map<String, ParamField> paramFields = new HashMap<String, ParamField>();

	private boolean isStartFlow = false;
	private HttpServletRequest request;
	private String processName = "通用";

	private String activityName = "开始";

	private String destName = null;
	private String transitionName;
	private String taskId;
	private String piId;

	public FlowRunInfo(HttpServletRequest req) {
		if ("true".equals(req.getParameter("startFlow"))) {
			this.isStartFlow = true;
		}

		String signUserIds = req.getParameter("signUserIds");
		if (StringUtils.isNotEmpty(signUserIds)) {
			this.variables.put("signUserIds", signUserIds);
		}

		String flowAssignId = req.getParameter("flowAssignId");
		if (StringUtils.isNotEmpty(flowAssignId)) {
			this.variables.put("flowAssignId", flowAssignId);
		}

		String signUserId = req.getParameter("");

		String pTaskId = req.getParameter("taskId");
		if (StringUtils.isNotEmpty(pTaskId)) {
			this.taskId = pTaskId;
		}

		String pPiId = req.getParameter("piId");

		if (StringUtils.isNotEmpty(pPiId)) {
			this.piId = pPiId;
		}

		String pActivityName = req.getParameter("activityName");
		if (StringUtils.isNotEmpty(pActivityName)) {
			this.activityName = pActivityName;
		}

		String pTaskName = req.getParameter("taskName");
		if (StringUtils.isNotEmpty(pTaskName)) {
			this.activityName = pTaskName;
		}

		String pDestName = req.getParameter("destName");

		if (StringUtils.isNotEmpty(pDestName)) {
			this.destName = pDestName;
		}

		String pSignName = req.getParameter("signalName");
		if (StringUtils.isNotEmpty(pSignName))
			this.transitionName = pSignName;
	}

	public FlowRunInfo() {
	}

	public Map<String, Object> getVariables() {
		return this.variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public boolean isStartFlow() {
		return this.isStartFlow;
	}

	public void setStartFlow(boolean isStartFlow) {
		this.isStartFlow = isStartFlow;
	}

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Map<String, ParamField> getParamFields() {
		return this.paramFields;
	}

	public void setParamFields(Map<String, ParamField> paramFields) {
		this.paramFields = paramFields;
	}

	public String getTransitionName() {
		return this.transitionName;
	}

	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getPiId() {
		return this.piId;
	}

	public void setPiId(String piId) {
		this.piId = piId;
	}

	public String getDestName() {
		return this.destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public void setdAssignId(String assignId) {
		this.variables.put("flowAssignId", assignId);
	}

	public void setMultipleTask(String userIds) {
		this.variables.put("signUserIds", userIds);
	}
}
