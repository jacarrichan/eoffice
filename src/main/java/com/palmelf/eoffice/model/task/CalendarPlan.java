package com.palmelf.eoffice.model.task;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "calendar_plan")
public class CalendarPlan extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4620346575099340429L;

	public static final Short STATUS_UNFINISHED = 0;

	public static final Short STATUS_FINISHED = 1;

	public static final Short URGENT_COMMON = 0;

	public static final Short URGENT_IMPORTANT = 1;

	public static final Short URGENT_INST = 2;

	@Expose
	private Long planId;

	@Expose
	private Date startTime;

	@Expose
	private Date endTime;

	@Expose
	private Short urgent;

	@Expose
	private String content;

	@Expose
	private Short status;

	@Expose
	private String fullname;

	@Expose
	private Long assignerId;

	@Expose
	private String assignerName;

	@Expose
	private String feedback;

	@Expose
	private Short showStyle;

	@Expose
	private Short taskType;

	@Expose
	private Long userId;

	public String getColor() {
		if (CalendarPlan.STATUS_FINISHED.equals(this.status)) {
			return "#D5E4BD";
		}
		if (CalendarPlan.URGENT_INST.equals(this.urgent)) {
			return "#94B98D";
		}
		if (CalendarPlan.URGENT_IMPORTANT.equals(this.urgent)) {
			return "#FFBDB4";
		}
		return "#7799BF";
	}
	
	public void setColor(String color){
		
	}

	public String getStatusName() {
		if (this.status.equals(CalendarPlan.STATUS_FINISHED)) {
			return "完成";
		}
		return "未完成";
	}
	
	public void  setStatusName(String statusName){
		
	}

	
	
	public String getUrgentName() {
		if (this.urgent.equals(CalendarPlan.URGENT_COMMON)) {
			return "一般";
		}
		if (this.urgent.equals(CalendarPlan.URGENT_IMPORTANT)) {
			return "重要";
		}
		return "紧急";
	}
	
	public void setUrgentName(String urgentName){
		
	}

	public CalendarPlan() {
	}

	public CalendarPlan(Long in_planId) {
		this.setPlanId(in_planId);
	}

	@Id
	@GeneratedValue
	@Column(name = "planId", unique = true, nullable = false)
	public Long getPlanId() {
		return this.planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	@Column(name = "startTime", length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "urgent", nullable = false)
	public Short getUrgent() {
		return this.urgent;
	}

	public void setUrgent(Short urgent) {
		this.urgent = urgent;
	}

	@Column(name = "content", nullable = false, length = 1200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "fullname", length = 32)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "assignerId", nullable = false)
	public Long getAssignerId() {
		return this.assignerId;
	}

	public void setAssignerId(Long assignerId) {
		this.assignerId = assignerId;
	}

	@Column(name = "assignerName", length = 32)
	public String getAssignerName() {
		return this.assignerName;
	}

	public void setAssignerName(String assignerName) {
		this.assignerName = assignerName;
	}

	@Column(name = "feedback", length = 500)
	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Column(name = "showStyle", nullable = false)
	public Short getShowStyle() {
		return this.showStyle;
	}

	public void setShowStyle(Short showStyle) {
		this.showStyle = showStyle;
	}

	@Column(name = "taskType", nullable = false)
	public Short getTaskType() {
		return this.taskType;
	}

	public void setTaskType(Short taskType) {
		this.taskType = taskType;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CalendarPlan)) {
			return false;
		}
		CalendarPlan rhs = (CalendarPlan) object;
		return new EqualsBuilder().append(this.planId, rhs.planId).append(this.startTime, rhs.startTime)
				.append(this.endTime, rhs.endTime).append(this.urgent, rhs.urgent).append(this.content, rhs.content)
				.append(this.status, rhs.status).append(this.fullname, rhs.fullname).append(this.userId, rhs.userId)
				.append(this.assignerId, rhs.assignerId).append(this.assignerName, rhs.assignerName)
				.append(this.feedback, rhs.feedback).append(this.showStyle, rhs.showStyle)
				.append(this.taskType, rhs.taskType).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.planId).append(this.startTime)
				.append(this.endTime).append(this.urgent).append(this.content).append(this.status)
				.append(this.fullname).append(this.userId).append(this.assignerId).append(this.assignerName)
				.append(this.feedback).append(this.showStyle).append(this.taskType).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("planId", this.planId).append("startTime", this.startTime)
				.append("endTime", this.endTime).append("urgent", this.urgent).append("content", this.content)
				.append("status", this.status).append("fullname", this.fullname).append("assignerId", this.assignerId)
				.append("userId", this.userId).append("assignerName", this.assignerName)
				.append("feedback", this.feedback).append("showStyle", this.showStyle)
				.append("taskType", this.taskType).toString();
	}
}
