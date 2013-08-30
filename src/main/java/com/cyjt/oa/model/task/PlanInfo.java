package com.cyjt.oa.model.task;

import java.text.SimpleDateFormat;

public class PlanInfo {
	private Long planId;
	private String content;
	private String durationTime;
	private String status;
	private String urgent;

	public Long getPlanId() {
		return this.planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDurationTime() {
		return this.durationTime;
	}

	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrgent() {
		return this.urgent;
	}

	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}

	public PlanInfo() {
	}

	public PlanInfo(CalendarPlan cp) {
		this.planId = cp.getPlanId();
		this.content = cp.getContent();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		this.durationTime = (sdf.format(cp.getStartTime()) + "--" + sdf
				.format(cp.getEndTime()));
		this.urgent = cp.getUrgentName();
		this.status = cp.getStatusName();
	}
}
