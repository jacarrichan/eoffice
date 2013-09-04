package com.palmelf.eoffice.model.flow;

import java.util.HashMap;
import java.util.Map;

public class FlowStartInfo {
	private boolean isStartFlow = false;

	private Map<String, String> variables = new HashMap<String, String>();

	public FlowStartInfo(boolean isStartFlow, Map<String, String> variables) {
		this.isStartFlow = isStartFlow;
		this.variables = variables;
	}

	public FlowStartInfo(boolean isStartFlow) {
		this.isStartFlow = isStartFlow;
	}

	public FlowStartInfo() {
	}

	public boolean isStartFlow() {
		return this.isStartFlow;
	}

	public void setStartFlow(boolean isStartFlow) {
		this.isStartFlow = isStartFlow;
	}

	public Map<String, String> getVariables() {
		return this.variables;
	}

	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

	public void setdAssignId(String assignId) {
		this.variables.put("flowAssignId", assignId);
	}
}
