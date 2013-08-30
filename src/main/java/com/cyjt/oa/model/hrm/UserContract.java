package com.cyjt.oa.model.hrm;

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

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.FileAttach;

@Entity
@Table(name = "user_contract")
public class UserContract extends BaseModel {

	private static final long serialVersionUID = 8337681620729350344L;

	@Expose
	private Long contractId;

	@Expose
	private Long userId;

	@Expose
	private String contractNo;

	@Expose
	private String fullname;

	@Expose
	private Integer status;

	@Expose
	private String timeLimit;

	@Expose
	private Integer isCompeted;

	@Expose
	private Integer isSecret;

	@Expose
	private String breakBurden;

	@Expose
	private String otherItems;

	@Expose
	private String contractType;

	@Expose
	private Date signDate;

	@Expose
	private Date startDate;

	@Expose
	private Date expireDate;

	@Expose
	private Set<FileAttach> contractAttachs = new HashSet<FileAttach>();

	@Expose
	private Set<ContractEvent> contractEvents = new HashSet<ContractEvent>();

	public UserContract() {
	}

	public UserContract(Long in_contractId) {
		this.setContractId(in_contractId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "contractId", unique = true, nullable = false)
	public Long getContractId() {
		return this.contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	@Column(name = "contractNo", nullable = false, length = 64)
	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "fullname", length = 64)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "timeLimit", length = 64)
	public String getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Column(name = "isCompeted")
	public Integer getIsCompeted() {
		return this.isCompeted;
	}

	public void setIsCompeted(Integer isCompeted) {
		this.isCompeted = isCompeted;
	}

	@Column(name = "isSecret")
	public Integer getIsSecret() {
		return this.isSecret;
	}

	public void setIsSecret(Integer isSecret) {
		this.isSecret = isSecret;
	}

	@Column(name = "breakBurden", length = 4000)
	public String getBreakBurden() {
		return this.breakBurden;
	}

	public void setBreakBurden(String breakBurden) {
		this.breakBurden = breakBurden;
	}

	@Column(name = "otherItems", length = 4000)
	public String getOtherItems() {
		return this.otherItems;
	}

	public void setOtherItems(String otherItems) {
		this.otherItems = otherItems;
	}

	@Column(name = "contractType", length = 64)
	public String getContractType() {
		return this.contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@Column(name = "signDate", length = 19)
	public Date getSignDate() {
		return this.signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	@Column(name = "startDate", length = 19)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "expireDate", length = 19)
	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "contract_attach", joinColumns = { @JoinColumn(name = "contractId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "fileId", nullable = false, updatable = false) })
	public Set<FileAttach> getContractAttachs() {
		return this.contractAttachs;
	}

	public void setContractAttachs(Set<FileAttach> contractAttachs) {
		this.contractAttachs = contractAttachs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userContract")
	public Set<ContractEvent> getContractEvents() {
		return this.contractEvents;
	}

	public void setContractEvents(Set<ContractEvent> contractEvents) {
		this.contractEvents = contractEvents;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserContract)) {
			return false;
		}
		UserContract rhs = (UserContract) object;
		return new EqualsBuilder().append(this.contractId, rhs.contractId).append(this.contractNo, rhs.contractNo)
				.append(this.fullname, rhs.fullname).append(this.status, rhs.status)
				.append(this.timeLimit, rhs.timeLimit).append(this.isCompeted, rhs.isCompeted)
				.append(this.isSecret, rhs.isSecret).append(this.breakBurden, rhs.breakBurden)
				.append(this.otherItems, rhs.otherItems).append(this.contractType, rhs.contractType)
				.append(this.signDate, rhs.signDate).append(this.startDate, rhs.startDate)
				.append(this.expireDate, rhs.expireDate).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.contractId).append(this.contractNo)
				.append(this.fullname).append(this.status).append(this.timeLimit).append(this.isCompeted)
				.append(this.isSecret).append(this.breakBurden).append(this.otherItems).append(this.contractType)
				.append(this.signDate).append(this.startDate).append(this.expireDate).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("contractId", this.contractId).append("contractNo", this.contractNo)
				.append("fullname", this.fullname).append("status", this.status).append("timeLimit", this.timeLimit)
				.append("isCompeted", this.isCompeted).append("isSecret", this.isSecret)
				.append("breakBurden", this.breakBurden).append("otherItems", this.otherItems)
				.append("contractType", this.contractType).append("signDate", this.signDate)
				.append("startDate", this.startDate).append("expireDate", this.expireDate).toString();
	}
}
