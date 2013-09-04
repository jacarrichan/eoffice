package com.palmelf.core.jbpm.pv;

import java.io.Serializable;
import java.util.LinkedList;

public class ProcessForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private String activityName;
	private LinkedList<ParamInfo> params = new LinkedList();

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public LinkedList<ParamInfo> getParams() {
		return this.params;
	}

	public void setParams(LinkedList<ParamInfo> params) {
		this.params = params;
	}
}
