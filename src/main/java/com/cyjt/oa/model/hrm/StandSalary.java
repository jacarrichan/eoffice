package com.cyjt.oa.model.hrm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "stand_salary")
public class StandSalary extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2958601122551963106L;

	@Expose
	private Long standardId;

	@Expose
	private String standardNo;

	@Expose
	private String standardName;

	@Expose
	private BigDecimal totalMoney;

	@Expose
	private String framer;

	@Expose
	private Date setdownTime;

	@Expose
	private String checkName;

	@Expose
	private Date checkTime;

	@Expose
	private String modifyName;

	@Expose
	private Date modifyTime;

	@Expose
	private String checkOpinion;

	@Expose
	private Short status;

	@Expose
	private String memo;
	private Set<StandSalaryItem> standSalaryItems = new HashSet<StandSalaryItem>();

	public StandSalary() {
	}

	public StandSalary(Long in_standardId) {
		this.setStandardId(in_standardId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "standardId", unique = true, nullable = false)
	public Long getStandardId() {
		return this.standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	@Column(name = "standardNo", nullable = false, length = 128)
	public String getStandardNo() {
		return this.standardNo;
	}

	public void setStandardNo(String standardNo) {
		this.standardNo = standardNo;
	}

	@Column(name = "standardName", nullable = false, length = 128)
	public String getStandardName() {
		return this.standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	@Column(name = "totalMoney", nullable = false, precision = 18)
	public BigDecimal getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name = "framer", length = 64)
	public String getFramer() {
		return this.framer;
	}

	public void setFramer(String framer) {
		this.framer = framer;
	}

	@Column(name = "setdownTime", length = 19)
	public Date getSetdownTime() {
		return this.setdownTime;
	}

	public void setSetdownTime(Date setdownTime) {
		this.setdownTime = setdownTime;
	}

	@Column(name = "checkName", length = 64)
	public String getCheckName() {
		return this.checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	@Column(name = "checkTime", length = 19)
	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "modifyName", length = 64)
	public String getModifyName() {
		return this.modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	@Column(name = "modifyTime", length = 19)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "checkOpinion", length = 512)
	public String getCheckOpinion() {
		return this.checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "memo", length = 512)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "standSalary")
	public Set<StandSalaryItem> getStandSalaryItems() {
		return this.standSalaryItems;
	}

	public void setStandSalaryItems(Set<StandSalaryItem> standSalaryItems) {
		this.standSalaryItems = standSalaryItems;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof StandSalary)) {
			return false;
		}
		StandSalary rhs = (StandSalary) object;
		return new EqualsBuilder().append(this.standardId, rhs.standardId).append(this.standardNo, rhs.standardNo)
				.append(this.standardName, rhs.standardName).append(this.totalMoney, rhs.totalMoney)
				.append(this.framer, rhs.framer).append(this.setdownTime, rhs.setdownTime)
				.append(this.checkName, rhs.checkName).append(this.checkTime, rhs.checkTime)
				.append(this.modifyName, rhs.modifyName).append(this.modifyTime, rhs.modifyTime)
				.append(this.checkOpinion, rhs.checkOpinion).append(this.status, rhs.status)
				.append(this.memo, rhs.memo).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.standardId).append(this.standardNo)
				.append(this.standardName).append(this.totalMoney).append(this.framer).append(this.setdownTime)
				.append(this.checkName).append(this.checkTime).append(this.modifyName).append(this.modifyTime)
				.append(this.checkOpinion).append(this.status).append(this.memo).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("standardId", this.standardId).append("standardNo", this.standardNo)
				.append("standardName", this.standardName).append("totalMoney", this.totalMoney)
				.append("framer", this.framer).append("setdownTime", this.setdownTime)
				.append("checkName", this.checkName).append("checkTime", this.checkTime)
				.append("modifyName", this.modifyName).append("modifyTime", this.modifyTime)
				.append("checkOpinion", this.checkOpinion).append("status", this.status).append("memo", this.memo)
				.toString();
	}
}
