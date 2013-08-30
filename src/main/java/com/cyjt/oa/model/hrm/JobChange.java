package com.cyjt.oa.model.hrm;

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

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "job_change")
public class JobChange extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7945519809305972636L;
	private Long changeId;
	private Long profileId;
	private String profileNo;
	private String userName;
	private Long orgJobId;
	private String orgJobName;
	private Long newJobId;
	private String newJobName;
	private Long orgStandardId;
	private Long newStandardId;
	private String orgStandardNo;
	private String orgStandardName;
	private Long orgDepId;
	private String orgDepName;
	private BigDecimal orgTotalMoney;
	private String newStandardNo;
	private String newStandardName;
	private Long newDepId;
	private String newDepName;
	private BigDecimal newTotalMoney;
	private String changeReason;
	private String regName;
	private Date regTime;
	private String checkName;
	private Date checkTime;
	private String checkOpinion;
	private Short status;
	private String memo;

	public JobChange() {
	}

	public JobChange(Long in_changeId) {
		this.setChangeId(in_changeId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "changeId", unique = true, nullable = false)
	public Long getChangeId() {
		return this.changeId;
	}

	public void setChangeId(Long changeId) {
		this.changeId = changeId;
	}

	@Column(name = "profileId", nullable = false)
	public Long getProfileId() {
		return this.profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	@Column(name = "profileNo", nullable = false, length = 64)
	public String getProfileNo() {
		return this.profileNo;
	}

	public void setProfileNo(String profileNo) {
		this.profileNo = profileNo;
	}

	@Column(name = "userName", length = 64)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "orgJobId", nullable = false)
	public Long getOrgJobId() {
		return this.orgJobId;
	}

	public void setOrgJobId(Long orgJobId) {
		this.orgJobId = orgJobId;
	}

	@Column(name = "orgStandardId")
	public Long getOrgStandardId() {
		return this.orgStandardId;
	}

	public void setOrgStandardId(Long orgStandardId) {
		this.orgStandardId = orgStandardId;
	}

	@Column(name = "newStandardId")
	public Long getNewStandardId() {
		return this.newStandardId;
	}

	public void setNewStandardId(Long newStandardId) {
		this.newStandardId = newStandardId;
	}

	@Column(name = "orgJobName", nullable = false, length = 64)
	public String getOrgJobName() {
		return this.orgJobName;
	}

	public void setOrgJobName(String orgJobName) {
		this.orgJobName = orgJobName;
	}

	@Column(name = "newJobId", nullable = false)
	public Long getNewJobId() {
		return this.newJobId;
	}

	public void setNewJobId(Long newJobId) {
		this.newJobId = newJobId;
	}

	@Column(name = "newJobName", nullable = false, length = 64)
	public String getNewJobName() {
		return this.newJobName;
	}

	public void setNewJobName(String newJobName) {
		this.newJobName = newJobName;
	}

	@Column(name = "orgStandardNo", length = 64)
	public String getOrgStandardNo() {
		return this.orgStandardNo;
	}

	public void setOrgStandardNo(String orgStandardNo) {
		this.orgStandardNo = orgStandardNo;
	}

	@Column(name = "orgStandardName", length = 64)
	public String getOrgStandardName() {
		return this.orgStandardName;
	}

	public void setOrgStandardName(String orgStandardName) {
		this.orgStandardName = orgStandardName;
	}

	@Column(name = "orgDepId")
	public Long getOrgDepId() {
		return this.orgDepId;
	}

	public void setOrgDepId(Long orgDepId) {
		this.orgDepId = orgDepId;
	}

	@Column(name = "orgDepName", length = 128)
	public String getOrgDepName() {
		return this.orgDepName;
	}

	public void setOrgDepName(String orgDepName) {
		this.orgDepName = orgDepName;
	}

	@Column(name = "orgTotalMoney", precision = 18)
	public BigDecimal getOrgTotalMoney() {
		return this.orgTotalMoney;
	}

	public void setOrgTotalMoney(BigDecimal orgTotalMoney) {
		this.orgTotalMoney = orgTotalMoney;
	}

	@Column(name = "newStandardNo", length = 64)
	public String getNewStandardNo() {
		return this.newStandardNo;
	}

	public void setNewStandardNo(String newStandardNo) {
		this.newStandardNo = newStandardNo;
	}

	@Column(name = "newStandardName", length = 64)
	public String getNewStandardName() {
		return this.newStandardName;
	}

	public void setNewStandardName(String newStandardName) {
		this.newStandardName = newStandardName;
	}

	@Column(name = "newDepId")
	public Long getNewDepId() {
		return this.newDepId;
	}

	public void setNewDepId(Long newDepId) {
		this.newDepId = newDepId;
	}

	@Column(name = "newDepName", length = 128)
	public String getNewDepName() {
		return this.newDepName;
	}

	public void setNewDepName(String newDepName) {
		this.newDepName = newDepName;
	}

	@Column(name = "newTotalMoney", precision = 18)
	public BigDecimal getNewTotalMoney() {
		return this.newTotalMoney;
	}

	public void setNewTotalMoney(BigDecimal newTotalMoney) {
		this.newTotalMoney = newTotalMoney;
	}

	@Column(name = "changeReason", length = 1024)
	public String getChangeReason() {
		return this.changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	@Column(name = "regName", length = 64)
	public String getRegName() {
		return this.regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	@Column(name = "regTime", length = 19)
	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
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

	@Column(name = "checkOpinion", length = 512)
	public String getCheckOpinion() {
		return this.checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "memo", length = 1024)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof JobChange)) {
			return false;
		}
		JobChange rhs = (JobChange) object;
		return new EqualsBuilder().append(this.changeId, rhs.changeId).append(this.profileId, rhs.profileId)
				.append(this.profileNo, rhs.profileNo).append(this.userName, rhs.userName)
				.append(this.orgJobId, rhs.orgJobId).append(this.orgStandardId, rhs.orgStandardId)
				.append(this.newStandardId, rhs.newStandardId).append(this.orgJobName, rhs.orgJobName)
				.append(this.newJobId, rhs.newJobId).append(this.newJobName, rhs.newJobName)
				.append(this.orgStandardNo, rhs.orgStandardNo).append(this.orgStandardName, rhs.orgStandardName)
				.append(this.orgDepId, rhs.orgDepId).append(this.orgDepName, rhs.orgDepName)
				.append(this.orgTotalMoney, rhs.orgTotalMoney).append(this.newStandardNo, rhs.newStandardNo)
				.append(this.newStandardName, rhs.newStandardName).append(this.newDepId, rhs.newDepId)
				.append(this.newDepName, rhs.newDepName).append(this.newTotalMoney, rhs.newTotalMoney)
				.append(this.changeReason, rhs.changeReason).append(this.regName, rhs.regName)
				.append(this.regTime, rhs.regTime).append(this.checkName, rhs.checkName)
				.append(this.checkTime, rhs.checkTime).append(this.checkOpinion, rhs.checkOpinion)
				.append(this.status, rhs.status).append(this.memo, rhs.memo).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.changeId).append(this.profileId)
				.append(this.profileNo).append(this.userName).append(this.orgJobId).append(this.orgJobName)
				.append(this.newJobId).append(this.newJobName).append(this.orgStandardNo).append(this.orgStandardName)
				.append(this.orgDepId).append(this.orgStandardId).append(this.newStandardId).append(this.orgDepName)
				.append(this.orgTotalMoney).append(this.newStandardNo).append(this.newStandardName)
				.append(this.newDepId).append(this.newDepName).append(this.newTotalMoney).append(this.changeReason)
				.append(this.regName).append(this.regTime).append(this.checkName).append(this.checkTime)
				.append(this.checkOpinion).append(this.status).append(this.memo).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("changeId", this.changeId).append("profileId", this.profileId)
				.append("profileNo", this.profileNo).append("userName", this.userName)
				.append("orgJobId", this.orgJobId).append("orgJobName", this.orgJobName)
				.append("newJobId", this.newJobId).append("newJobName", this.newJobName)
				.append("orgStandardId", this.orgStandardId).append("newStandardId", this.newStandardId)
				.append("orgStandardNo", this.orgStandardNo).append("orgStandardName", this.orgStandardName)
				.append("orgDepId", this.orgDepId).append("orgDepName", this.orgDepName)
				.append("orgTotalMoney", this.orgTotalMoney).append("newStandardNo", this.newStandardNo)
				.append("newStandardName", this.newStandardName).append("newDepId", this.newDepId)
				.append("newDepName", this.newDepName).append("newTotalMoney", this.newTotalMoney)
				.append("changeReason", this.changeReason).append("regName", this.regName)
				.append("regTime", this.regTime).append("checkName", this.checkName)
				.append("checkTime", this.checkTime).append("checkOpinion", this.checkOpinion)
				.append("status", this.status).append("memo", this.memo).toString();
	}
}
