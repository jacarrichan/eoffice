package com.palmelf.eoffice.model.task;

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

import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.system.Department;

@Entity
@Table(name = "plan_attend")
public class PlanAttend extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9212293283028108664L;
	private Long attendId;
	private Short isDep;
	private Short isPrimary;
	private WorkPlan workPlan;
	private Department department;
	private AppUser appUser;

	public PlanAttend() {
	}

	public PlanAttend(Long in_attendId) {
		this.setAttendId(in_attendId);
	}

	@Id
	@GeneratedValue
	@Column(name = "attendId", unique = true, nullable = false)
	public Long getAttendId() {
		return this.attendId;
	}

	public void setAttendId(Long attendId) {
		this.attendId = attendId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planId")
	public WorkPlan getWorkPlan() {
		return this.workPlan;
	}

	public void setWorkPlan(WorkPlan workPlan) {
		this.workPlan = workPlan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depId")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "isDep", nullable = false)
	public Short getIsDep() {
		return this.isDep;
	}

	public void setIsDep(Short isDep) {
		this.isDep = isDep;
	}

	@Column(name = "isPrimary")
	public Short getIsPrimary() {
		return this.isPrimary;
	}

	public void setIsPrimary(Short isPrimary) {
		this.isPrimary = isPrimary;
	}
	@Transient
	public Long getDepId() {
		return this.getDepartment() == null ? null : this.getDepartment().getDepId();
	}

	public void setDepId(Long aValue) {
		if (aValue == null) {
			this.department = null;
		} else if (this.department == null) {
			this.department = new Department(aValue);
			this.department.setVersion(new Integer(0));
		} else {
			this.department.setDepId(aValue);
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
	@Transient
	public Long getPlanId() {
		return this.getWorkPlan() == null ? null : this.getWorkPlan().getPlanId();
	}

	public void setPlanId(Long aValue) {
		if (aValue == null) {
			this.workPlan = null;
		} else if (this.workPlan == null) {
			this.workPlan = new WorkPlan(aValue);
			this.workPlan.setVersion(new Integer(0));
		} else {
			this.workPlan.setPlanId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PlanAttend)) {
			return false;
		}
		PlanAttend rhs = (PlanAttend) object;
		return new EqualsBuilder().append(this.attendId, rhs.attendId).append(this.isDep, rhs.isDep)
				.append(this.isPrimary, rhs.isPrimary).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.attendId).append(this.isDep)
				.append(this.isPrimary).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("attendId", this.attendId).append("isDep", this.isDep)
				.append("isPrimary", this.isPrimary).toString();
	}
}
