package com.cyjt.oa.model.admin;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "car_apply")
public class CarApply extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2044843945723471702L;
	public static Short PASS_APPLY = 1;
	public static Short NOTPASS_APPLY = 2;
	public static Short INIT_APPLY = 0;

	@Expose
	private Long applyId;

	@Expose
	private String department;

	@Expose
	private String userFullname;

	@Expose
	private Date applyDate;

	@Expose
	private String reason;

	@Expose
	private Date startTime;

	@Expose
	private Date endTime;

	@Expose
	private String proposer;

	@Expose
	private Long userId;

	@Expose
	private BigDecimal mileage;

	@Expose
	private BigDecimal oilUse;

	@Expose
	private String notes;

	@Expose
	private Short approvalStatus;

	@Expose
	private Car car;

	public CarApply() {
	}

	public CarApply(Long in_applyId) {
		this.setApplyId(in_applyId);
	}

	@Id
	@GeneratedValue
	@Column(name = "applyId", unique = true, nullable = false)
	public Long getApplyId() {
		return this.applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "carId")
	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Column(name = "department", nullable = false, length = 64)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "userFullname", nullable = false, length = 32)
	public String getUserFullname() {
		return this.userFullname;
	}

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	@Column(name = "applyDate", nullable = false, length = 19)
	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	@Column(name = "reason", nullable = false, length = 512)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "startTime", nullable = false, length = 19)
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

	@Column(name = "proposer", nullable = false, length = 32)
	public String getProposer() {
		return this.proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	@Column(name = "mileage", precision = 18)
	public BigDecimal getMileage() {
		return this.mileage;
	}

	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}

	@Column(name = "oilUse", precision = 18)
	public BigDecimal getOilUse() {
		return this.oilUse;
	}

	public void setOilUse(BigDecimal oilUse) {
		this.oilUse = oilUse;
	}

	@Column(name = "notes", length = 128)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "approvalStatus", nullable = false)
	public Short getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(Short approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CarApply)) {
			return false;
		}
		CarApply rhs = (CarApply) object;
		return new EqualsBuilder().append(this.applyId, rhs.applyId).append(this.department, rhs.department)
				.append(this.userFullname, rhs.userFullname).append(this.applyDate, rhs.applyDate)
				.append(this.reason, rhs.reason).append(this.startTime, rhs.startTime)
				.append(this.endTime, rhs.endTime).append(this.proposer, rhs.proposer).append(this.userId, rhs.userId)
				.append(this.mileage, rhs.mileage).append(this.oilUse, rhs.oilUse).append(this.notes, rhs.notes)
				.append(this.approvalStatus, rhs.approvalStatus).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.applyId).append(this.department)
				.append(this.userFullname).append(this.applyDate).append(this.reason).append(this.startTime)
				.append(this.endTime).append(this.proposer).append(this.userId).append(this.mileage)
				.append(this.oilUse).append(this.notes).append(this.approvalStatus).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("applyId", this.applyId).append("department", this.department)
				.append("userFullname", this.userFullname).append("applyDate", this.applyDate)
				.append("reason", this.reason).append("startTime", this.startTime).append("endTime", this.endTime)
				.append("proposer", this.proposer).append("userId", this.userId).append("mileage", this.mileage)
				.append("oilUse", this.oilUse).append("notes", this.notes)
				.append("approvalStatus", this.approvalStatus).toString();
	}
}
