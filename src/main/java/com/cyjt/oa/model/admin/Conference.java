package com.cyjt.oa.model.admin;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.FileAttach;

@Entity
@Table(name = "conference")
public class Conference extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3715462603143743717L;

	public static final Short ISNOEMAIL = 0;

	public static final Short ISNOMOBILE = 0;

	public static final Short SEND = 1;

	public static final Short Apply = 2;

	public static final Short TEMP = 0;
	private Long confId;
	private String confTopic;
	private String confProperty;
	private Short importLevel;
	private Double feeBudget;
	private String compere;
	private String recorder;
	private String attendUsers;
	private Short status;
	private Short isEmail;
	private Short isMobile;
	private Date startTime;
	private Date endTime;
	private Long roomId;
	private String roomName;
	private String roomLocation;
	private String confContent;
	private String compereName;
	private String recorderName;
	private String attendUsersName;
	private Long checkUserId;
	private String checkName;
	private String checkReason;
	private Date createtime;
	private Date sendtime;
	private Long typeId;
	private Set<ConfAttend> confAttends = new HashSet<ConfAttend>();
	private Set<ConfSummary> confSummaries = new HashSet<ConfSummary>();
	private Set<ConfPrivilege> confPrivileges = new HashSet<ConfPrivilege>();
	private Set<FileAttach> fileAttachs = new HashSet<FileAttach>();

	public Conference() {
	}

	public Conference(Long in_confId) {
		this.setConfId(in_confId);
	}

	@Id
	@GeneratedValue
	@Column(name = "confId", unique = true, nullable = false)
	public Long getConfId() {
		return this.confId;
	}

	public void setConfId(Long confId) {
		this.confId = confId;
	}

	@Column(name = "roomId", nullable = false)
	public Long getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	@Column(name = "confProperty", nullable = false, length = 128)
	public String getConfProperty() {
		return this.confProperty;
	}

	public void setConfProperty(String confProperty) {
		this.confProperty = confProperty;
	}

	@Column(name = "confTopic", nullable = false, length = 128)
	public String getConfTopic() {
		return this.confTopic;
	}

	public void setConfTopic(String confTopic) {
		this.confTopic = confTopic;
	}

	@Column(name = "importLevel", nullable = false)
	public Short getImportLevel() {
		return this.importLevel;
	}

	public void setImportLevel(Short importLevel) {
		this.importLevel = importLevel;
	}

	@Column(name = "feeBudget", precision = 22, scale = 0)
	public Double getFeeBudget() {
		return this.feeBudget;
	}

	public void setFeeBudget(Double feeBudget) {
		this.feeBudget = feeBudget;
	}

	@Column(name = "compere", length = 64)
	public String getCompere() {
		return this.compere;
	}

	public void setCompere(String compere) {
		this.compere = compere;
	}

	@Column(name = "recorder", length = 64)
	public String getRecorder() {
		return this.recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	@Column(name = "attendUsers", length = 500)
	public String getAttendUsers() {
		return this.attendUsers;
	}

	public void setAttendUsers(String attendUsers) {
		this.attendUsers = attendUsers;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "isEmail")
	public Short getIsEmail() {
		return this.isEmail;
	}

	public void setIsEmail(Short isEmail) {
		this.isEmail = isEmail;
	}

	@Column(name = "isMobile")
	public Short getIsMobile() {
		return this.isMobile;
	}

	public void setIsMobile(Short isMobile) {
		this.isMobile = isMobile;
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

	@Column(name = "roomName", length = 64)
	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Column(name = "roomLocation", length = 128)
	public String getRoomLocation() {
		return this.roomLocation;
	}

	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}

	@Column(name = "confContent")
	public String getConfContent() {
		return this.confContent;
	}

	public void setConfContent(String confContent) {
		this.confContent = confContent;
	}

	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "sendtime", length = 19)
	public Date getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	@Column(name = "compereName", length = 256)
	public String getCompereName() {
		return this.compereName;
	}

	public void setCompereName(String compereName) {
		this.compereName = compereName;
	}

	@Column(name = "recorderName", length = 256)
	public String getRecorderName() {
		return this.recorderName;
	}

	public void setRecorderName(String recorderName) {
		this.recorderName = recorderName;
	}

	@Column(name = "attendUsersName", length = 4000)
	public String getAttendUsersName() {
		return this.attendUsersName;
	}

	public void setAttendUsersName(String attendUsersName) {
		this.attendUsersName = attendUsersName;
	}

	@Column(name = "checkUserId")
	public Long getCheckUserId() {
		return this.checkUserId;
	}

	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}

	@Column(name = "checkName", length = 64)
	public String getCheckName() {
		return this.checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	@Column(name = "checkReason", length = 512)
	public String getCheckReason() {
		return this.checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	@Column(name = "typeId", nullable = false)
	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "conference")
	public Set<ConfAttend> getConfAttends() {
		return this.confAttends;
	}

	public void setConfAttends(Set<ConfAttend> confAttends) {
		this.confAttends = confAttends;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "confId")
	public Set<ConfSummary> getConfSummaries() {
		return this.confSummaries;
	}

	public void setConfSummaries(Set<ConfSummary> confSummaries) {
		this.confSummaries = confSummaries;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "confId")
	public Set<ConfPrivilege> getConfPrivileges() {
		return this.confPrivileges;
	}

	public void setConfPrivileges(Set<ConfPrivilege> confPrivileges) {
		this.confPrivileges = confPrivileges;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "conf_attach", joinColumns = { @JoinColumn(name = "confId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "fileId", nullable = false, updatable = false) })
	public Set<FileAttach> getFileAttachs() {
		return this.fileAttachs;
	}

	public void setFileAttachs(Set<FileAttach> fileAttachs) {
		this.fileAttachs = fileAttachs;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Conference)) {
			return false;
		}
		Conference rhs = (Conference) object;
		return new EqualsBuilder().append(this.confId, rhs.confId).append(this.confTopic, rhs.confTopic)
				.append(this.confProperty, rhs.confProperty).append(this.importLevel, rhs.importLevel)
				.append(this.feeBudget, rhs.feeBudget).append(this.compere, rhs.compere)
				.append(this.recorder, rhs.recorder).append(this.attendUsers, rhs.attendUsers)
				.append(this.status, rhs.status).append(this.isEmail, rhs.isEmail).append(this.isMobile, rhs.isMobile)
				.append(this.startTime, rhs.startTime).append(this.endTime, rhs.endTime)
				.append(this.roomId, rhs.roomId).append(this.roomName, rhs.roomName)
				.append(this.roomLocation, rhs.roomLocation).append(this.confContent, rhs.confContent)
				.append(this.compereName, rhs.compereName).append(this.recorderName, rhs.recorderName)
				.append(this.attendUsersName, rhs.attendUsersName).append(this.checkUserId, rhs.checkUserId)
				.append(this.checkName, rhs.checkName).append(this.checkReason, rhs.checkReason)
				.append(this.createtime, rhs.createtime).append(this.sendtime, rhs.sendtime)
				.append(this.typeId, rhs.typeId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.confId).append(this.confTopic)
				.append(this.confProperty).append(this.importLevel).append(this.feeBudget).append(this.compere)
				.append(this.recorder).append(this.attendUsers).append(this.status).append(this.isEmail)
				.append(this.isMobile).append(this.startTime).append(this.endTime).append(this.roomId)
				.append(this.roomName).append(this.roomLocation).append(this.confContent).append(this.compereName)
				.append(this.recorderName).append(this.attendUsersName).append(this.checkUserId).append(this.checkName)
				.append(this.checkReason).append(this.createtime).append(this.sendtime).append(this.typeId)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("confId", this.confId).append("confTopic", this.confTopic)
				.append("confProperty", this.confProperty).append("importLevel", this.importLevel)
				.append("feeBudget", this.feeBudget).append("compere", this.compere).append("recorder", this.recorder)
				.append("attendUsers", this.attendUsers).append("status", this.status).append("isEmail", this.isEmail)
				.append("isMobile", this.isMobile).append("startTime", this.startTime).append("endTime", this.endTime)
				.append("roomId", this.roomId).append("roomName", this.roomName)
				.append("roomLocation", this.roomLocation).append("confContent", this.confContent)
				.append("compereName", this.compereName).append("recorderName", this.recorderName)
				.append("attendUsersName", this.attendUsersName).append("checkUserId", this.checkUserId)
				.append("checkName", this.checkName).append("checkReason", this.checkReason)
				.append("createtime", this.createtime).append("sendtime", this.sendtime).append("typeId", this.typeId)
				.toString();
	}
}
