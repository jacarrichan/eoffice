package com.palmelf.core.jbpm.pv;

import java.util.Date;
import org.jbpm.pvm.internal.task.TaskImpl;

public class TaskInfo {
	private String taskName;
	private String activityName;
	private String assignee;
	private Date createTime;
	private Date dueDate;
	private String executionId;
	private String pdId;
	private Long taskId;
	private Short isMultipleTask = 0;

	private String candidateUsers = "";

	private String candidateRoles = "";

	public TaskInfo() {
	}

	public TaskInfo(TaskImpl taskImpl) {
		this.taskName = taskImpl.getActivityName();

		this.activityName = taskImpl.getActivityName();
		this.assignee = taskImpl.getAssignee();
		this.dueDate = taskImpl.getDuedate();
		this.createTime = taskImpl.getCreateTime();
		if (taskImpl.getSuperTask() != null) {
			this.pdId = taskImpl.getSuperTask().getProcessInstance().getId();
			this.executionId = taskImpl.getSuperTask().getExecutionId();
		} else {
			this.pdId = taskImpl.getProcessInstance().getId();
			this.executionId = taskImpl.getExecutionId();
		}

		this.taskId = Long.valueOf(taskImpl.getDbid());

		if (taskImpl.getParticipations().size() > 0)
			this.isMultipleTask = 1;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getAssignee() {
		return this.assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getExecutionId() {
		return this.executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getCandidateUsers() {
		return this.candidateUsers;
	}

	public void setCandidateUsers(String candidateUsers) {
		this.candidateUsers = candidateUsers;
	}

	public String getCandidateRoles() {
		return this.candidateRoles;
	}

	public void setCandidateRoles(String candidateRoles) {
		this.candidateRoles = candidateRoles;
	}

	public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Short getIsMultipleTask() {
		return this.isMultipleTask;
	}

	public void setIsMultipleTask(Short isMultipleTask) {
		this.isMultipleTask = isMultipleTask;
	}

	public String getPdId() {
		return this.pdId;
	}

	public void setPdId(String pdId) {
		this.pdId = pdId;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
