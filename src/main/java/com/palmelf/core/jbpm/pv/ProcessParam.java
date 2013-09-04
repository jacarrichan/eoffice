package com.palmelf.core.jbpm.pv;

import java.io.Serializable;
import java.util.Date;

public class ProcessParam implements Serializable {
	public static final String PARAM_NAME = "_pp";
	private static final long serialVersionUID = 1L;
	private UserInfo user;
	private Date createtime;
	private String processName;
	private String activityName;
	private ProcessForm processForm;

	public UserInfo getUser() {
		return this.user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public ProcessForm getProcessForm() {
		return this.processForm;
	}

	public void setProcessForm(ProcessForm processForm) {
		this.processForm = processForm;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
}
