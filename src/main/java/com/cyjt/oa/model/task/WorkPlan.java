package com.cyjt.oa.model.task;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.FileAttach;

@Entity
@Table(name = "work_plan")
public class WorkPlan extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5733242755530238491L;

	@Expose
	private Long planId;

	@Expose
	private String planName;

	@Expose
	private String planContent;

	@Expose
	private Date startTime;

	@Expose
	private Date endTime;

	@Expose
	private String issueScope;

	@Expose
	private String participants;

	@Expose
	private String principal;

	@Expose
	private String note;

	@Expose
	private Short status;

	@Expose
	private Short isPersonal;

	@Expose
	private String icon;

	@Expose
	private PlanType planType;

	@Expose
	private AppUser appUser;

	@Expose
	private Set<FileAttach> planFiles = new HashSet<FileAttach>();
	private Set<PlanAttend> planAttends = new HashSet<PlanAttend>();

	public WorkPlan() {
	}

	public WorkPlan(Long in_planId) {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId")
	public PlanType getPlanType() {
		return this.planType;
	}

	public void setPlanType(PlanType planType) {
		this.planType = planType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "planName", nullable = false, length = 128)
	public String getPlanName() {
		return this.planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	@Column(name = "planContent", length = 5000)
	public String getPlanContent() {
		return this.planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
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

	@Column(name = "issueScope", length = 2000)
	public String getIssueScope() {
		return this.issueScope;
	}

	public void setIssueScope(String issueScope) {
		this.issueScope = issueScope;
	}

	@Column(name = "participants", length = 2000)
	public String getParticipants() {
		return this.participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	@Column(name = "principal", nullable = false, length = 256)
	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Column(name = "note", length = 500)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "isPersonal", nullable = false)
	public Short getIsPersonal() {
		return this.isPersonal;
	}

	public void setIsPersonal(Short isPersonal) {
		this.isPersonal = isPersonal;
	}

	@Column(name = "icon", length = 128)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "workPlan")
	public Set<PlanAttend> getPlanAttends() {
		return this.planAttends;
	}

	public void setPlanAttends(Set<PlanAttend> planAttends) {
		this.planAttends = planAttends;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "plan_file", joinColumns = { @JoinColumn(name = "planId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "fileId", nullable = false, updatable = false) })
	public Set<FileAttach> getPlanFiles() {
		return this.planFiles;
	}

	public void setPlanFiles(Set<FileAttach> planFiles) {
		this.planFiles = planFiles;
	}
	@Transient
	public Long getTypeId() {
		return this.getPlanType() == null ? null : this.getPlanType().getTypeId();
	}

	public void setTypeId(Long aValue) {
		if (aValue == null) {
			this.planType = null;
		} else if (this.planType == null) {
			this.planType = new PlanType(aValue);
			this.planType.setVersion(new Integer(0));
		} else {
			this.planType.setTypeId(aValue);
		}
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
		if (!(object instanceof WorkPlan)) {
			return false;
		}
		WorkPlan rhs = (WorkPlan) object;
		return new EqualsBuilder().append(this.planId, rhs.planId).append(this.planName, rhs.planName)
				.append(this.planContent, rhs.planContent).append(this.startTime, rhs.startTime)
				.append(this.endTime, rhs.endTime).append(this.issueScope, rhs.issueScope)
				.append(this.participants, rhs.participants).append(this.principal, rhs.principal)
				.append(this.note, rhs.note).append(this.status, rhs.status).append(this.isPersonal, rhs.isPersonal)
				.append(this.icon, rhs.icon).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.planId).append(this.planName)
				.append(this.planContent).append(this.startTime).append(this.endTime).append(this.issueScope)
				.append(this.participants).append(this.principal).append(this.note).append(this.status)
				.append(this.isPersonal).append(this.icon).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("planId", this.planId).append("planName", this.planName)
				.append("planContent", this.planContent).append("startTime", this.startTime)
				.append("endTime", this.endTime).append("issueScope", this.issueScope)
				.append("participants", this.participants).append("principal", this.principal)
				.append("note", this.note).append("status", this.status).append("isPersonal", this.isPersonal)
				.append("icon", this.icon).toString();
	}
}
