package com.palmelf.eoffice.model.hrm;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "salary_payoff")
public class SalaryPayoff extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4497402679441908915L;
	public static short CHECK_FLAG_NONE = 0;
	public static short CHECK_FLAG_PASS = 1;
	public static short CHECK_FLAG_NOT_PASS = 2;
	private Long recordId;
	private String fullname;
	private Long userId;
	private String profileNo;
	private String idNo;
	private BigDecimal standAmount;
	private BigDecimal encourageAmount;
	private BigDecimal deductAmount;
	private BigDecimal achieveAmount;
	private String encourageDesc;
	private String deductDesc;
	private String memo;
	private BigDecimal acutalAmount;
	private Date regTime;
	private String register;
	private String checkName;
	private Date checkTime;
	private Short checkStatus;
	private Date startTime;
	private Date endTime;
	private String checkOpinion;
	private Long standardId;

	public SalaryPayoff() {
	}

	public SalaryPayoff(Long in_recordId) {
		this.setRecordId(in_recordId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "recordId", unique = true, nullable = false)
	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	@Column(name = "fullname", nullable = false, length = 64)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "profileNo", length = 128)
	public String getProfileNo() {
		return this.profileNo;
	}

	public void setProfileNo(String profileNo) {
		this.profileNo = profileNo;
	}

	@Column(name = "idNo", length = 128)
	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column(name = "standAmount", nullable = false, precision = 18)
	public BigDecimal getStandAmount() {
		return this.standAmount;
	}

	public void setStandAmount(BigDecimal standAmount) {
		this.standAmount = standAmount;
	}

	@Column(name = "encourageAmount", nullable = false, precision = 18)
	public BigDecimal getEncourageAmount() {
		return this.encourageAmount;
	}

	public void setEncourageAmount(BigDecimal encourageAmount) {
		this.encourageAmount = encourageAmount;
	}

	@Column(name = "deductAmount", nullable = false, precision = 18)
	public BigDecimal getDeductAmount() {
		return this.deductAmount;
	}

	public void setDeductAmount(BigDecimal deductAmount) {
		this.deductAmount = deductAmount;
	}

	@Column(name = "achieveAmount", precision = 18)
	public BigDecimal getAchieveAmount() {
		return this.achieveAmount;
	}

	public void setAchieveAmount(BigDecimal achieveAmount) {
		this.achieveAmount = achieveAmount;
	}

	@Column(name = "encourageDesc", length = 512)
	public String getEncourageDesc() {
		return this.encourageDesc;
	}

	public void setEncourageDesc(String encourageDesc) {
		this.encourageDesc = encourageDesc;
	}

	@Column(name = "deductDesc", length = 512)
	public String getDeductDesc() {
		return this.deductDesc;
	}

	public void setDeductDesc(String deductDesc) {
		this.deductDesc = deductDesc;
	}

	@Column(name = "memo", length = 512)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "acutalAmount", precision = 18)
	public BigDecimal getAcutalAmount() {
		return this.acutalAmount;
	}

	public void setAcutalAmount(BigDecimal acutalAmount) {
		this.acutalAmount = acutalAmount;
	}

	@Column(name = "regTime", nullable = false, length = 19)
	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	@Column(name = "register", length = 64)
	public String getRegister() {
		return this.register;
	}

	public void setRegister(String register) {
		this.register = register;
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

	@Column(name = "checkStatus")
	public Short getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(Short checkStatus) {
		this.checkStatus = checkStatus;
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

	@Column(name = "checkOpinion", length = 1024)
	public String getCheckOpinion() {
		return this.checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}

	@Column(name = "standardId", nullable = false)
	public Long getStandardId() {
		return this.standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SalaryPayoff)) {
			return false;
		}
		SalaryPayoff rhs = (SalaryPayoff) object;
		return new EqualsBuilder().append(this.recordId, rhs.recordId).append(this.fullname, rhs.fullname)
				.append(this.userId, rhs.userId).append(this.profileNo, rhs.profileNo).append(this.idNo, rhs.idNo)
				.append(this.standAmount, rhs.standAmount).append(this.encourageAmount, rhs.encourageAmount)
				.append(this.deductAmount, rhs.deductAmount).append(this.achieveAmount, rhs.achieveAmount)
				.append(this.encourageDesc, rhs.encourageDesc).append(this.deductDesc, rhs.deductDesc)
				.append(this.memo, rhs.memo).append(this.acutalAmount, rhs.acutalAmount)
				.append(this.regTime, rhs.regTime).append(this.register, rhs.register)
				.append(this.checkName, rhs.checkName).append(this.checkTime, rhs.checkTime)
				.append(this.checkStatus, rhs.checkStatus).append(this.startTime, rhs.startTime)
				.append(this.endTime, rhs.endTime).append(this.checkOpinion, rhs.checkOpinion)
				.append(this.standardId, rhs.standardId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.recordId).append(this.fullname)
				.append(this.userId).append(this.profileNo).append(this.idNo).append(this.standAmount)
				.append(this.encourageAmount).append(this.deductAmount).append(this.achieveAmount)
				.append(this.encourageDesc).append(this.deductDesc).append(this.memo).append(this.acutalAmount)
				.append(this.regTime).append(this.register).append(this.checkName).append(this.checkTime)
				.append(this.checkStatus).append(this.startTime).append(this.endTime).append(this.checkOpinion)
				.append(this.standardId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("recordId", this.recordId).append("fullname", this.fullname)
				.append("userId", this.userId).append("profileNo", this.profileNo).append("idNo", this.idNo)
				.append("standAmount", this.standAmount).append("encourageAmount", this.encourageAmount)
				.append("deductAmount", this.deductAmount).append("achieveAmount", this.achieveAmount)
				.append("encourageDesc", this.encourageDesc).append("deductDesc", this.deductDesc)
				.append("memo", this.memo).append("acutalAmount", this.acutalAmount).append("regTime", this.regTime)
				.append("register", this.register).append("checkName", this.checkName)
				.append("checkTime", this.checkTime).append("checkStatus", this.checkStatus)
				.append("startTime", this.startTime).append("endTime", this.endTime)
				.append("checkOpinion", this.checkOpinion).append("standardId", this.standardId).toString();
	}
}
