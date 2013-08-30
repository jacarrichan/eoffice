package com.cyjt.core.jbpm.pv;

import java.util.ArrayList;
import java.util.List;

public class TaskExeMapping {
	private Long taskId;
	private List<Long> userIds = new ArrayList();

	private List<Long> roleIds = new ArrayList();

	public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public List<Long> getUserIds() {
		return this.userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}

	public List<Long> getRoleIds() {
		return this.roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}
}
