package com.palmelf.eoffice.model.hrm;

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
import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "contract_event")
public class ContractEvent extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7888720344796384366L;

	@Expose
	private Long eventId;

	@Expose
	private String eventName;

	@Expose
	private String eventDescp;

	@Expose
	private Date createTime;

	@Expose
	private String creator;
	private UserContract userContract;

	public ContractEvent() {
	}

	public ContractEvent(Long in_eventId) {
		this.setEventId(in_eventId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "eventId", unique = true, nullable = false)
	public Long getEventId() {
		return this.eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contractId")
	public UserContract getUserContract() {
		return this.userContract;
	}

	public void setUserContract(UserContract userContract) {
		this.userContract = userContract;
	}

	@Column(name = "eventName", nullable = false, length = 64)
	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Column(name = "eventDescp", nullable = false)
	public String getEventDescp() {
		return this.eventDescp;
	}

	public void setEventDescp(String eventDescp) {
		this.eventDescp = eventDescp;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "creator", nullable = false, length = 64)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@Transient
	public Long getContractId() {
		return getUserContract() == null ? null : getUserContract()
				.getContractId();
	}

	public void setContractId(Long aValue) {
		if (aValue == null) {
			this.userContract = null;
		} else if (this.userContract == null) {
			this.userContract = new UserContract(aValue);
			this.userContract.setVersion(new Integer(0));
		} else {
			this.userContract.setContractId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ContractEvent)) {
			return false;
		}
		ContractEvent rhs = (ContractEvent) object;
		return new EqualsBuilder().append(this.eventId, rhs.eventId)
				.append(this.eventName, rhs.eventName)
				.append(this.eventDescp, rhs.eventDescp)
				.append(this.createTime, rhs.createTime)
				.append(this.creator, rhs.creator).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.eventId)
				.append(this.eventName).append(this.eventDescp)
				.append(this.createTime).append(this.creator).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("eventId", this.eventId)
				.append("eventName", this.eventName)
				.append("eventDescp", this.eventDescp)
				.append("createTime", this.createTime)
				.append("creator", this.creator).toString();
	}
}
