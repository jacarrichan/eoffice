package com.cyjt.oa.model.task;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.AppUser;

@Entity
@Table(name = "appointment")
public class Appointment extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -482347540074622118L;

	@Expose
	private Long appointId;

	@Expose
	private String subject;

	@Expose
	private Date startTime;

	@Expose
	private Date endTime;

	@Expose
	private String content;

	@Expose
	private String notes;

	@Expose
	private String location;

	@Expose
	private String inviteEmails;

	@Expose
	private AppUser appUser;

	@Expose
	private Integer isMsg;

	@Expose
	private Integer isMobile;

	public Appointment() {
	}

	public Appointment(Long in_appointId) {
		this.setAppointId(in_appointId);
	}

	@Id
	@GeneratedValue
	@Column(name = "appointId", unique = true, nullable = false)
	public Long getAppointId() {
		return this.appointId;
	}

	public void setAppointId(Long appointId) {
		this.appointId = appointId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "subject", nullable = false, length = 128)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "startTime", nullable = false, length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime", nullable = false, length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "content", nullable = false, length = 5000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "notes", length = 1000)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "location", nullable = false, length = 150)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "inviteEmails", length = 1000)
	public String getInviteEmails() {
		return this.inviteEmails;
	}

	public void setInviteEmails(String inviteEmails) {
		this.inviteEmails = inviteEmails;
	}

	@Column(name = "isMsg")
	public Integer getIsMsg() {
		return this.isMsg;
	}

	public void setIsMsg(Integer isMsg) {
		this.isMsg = isMsg;
	}

	@Column(name = "isMobile")
	public Integer getIsMobile() {
		return this.isMobile;
	}

	public void setIsMobile(Integer isMobile) {
		this.isMobile = isMobile;
	}
	@Transient
	public Long getUserId() {
		return this.getAppUser() == null ? null : this.getAppUser().getUserId();
	}

	public void setUserId(Long aValue) {
		if (aValue == null) {
			this.appUser = null;
		} else if (this.appUser == null) {
			this.appUser = new AppUser(aValue);
			this.appUser.setVersion(new Integer(0));
		} else {
			this.appUser.setUserId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Appointment)) {
			return false;
		}
		Appointment rhs = (Appointment) object;
		return new EqualsBuilder().append(this.appointId, rhs.appointId).append(this.subject, rhs.subject)
				.append(this.startTime, rhs.startTime).append(this.endTime, rhs.endTime)
				.append(this.content, rhs.content).append(this.notes, rhs.notes).append(this.location, rhs.location)
				.append(this.inviteEmails, rhs.inviteEmails).append(this.isMsg, rhs.isMsg)
				.append(this.isMobile, rhs.isMobile).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.appointId).append(this.subject)
				.append(this.startTime).append(this.endTime).append(this.content).append(this.notes)
				.append(this.location).append(this.inviteEmails).append(this.isMsg).append(this.isMobile).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("appointId", this.appointId).append("subject", this.subject)
				.append("startTime", this.startTime).append("endTime", this.endTime).append("content", this.content)
				.append("notes", this.notes).append("location", this.location)
				.append("inviteEmails", this.inviteEmails).append("isMsg", this.isMsg)
				.append("isMobile", this.isMobile).toString();
	}
}
