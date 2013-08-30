package com.cyjt.oa.model.customer;

import java.math.BigDecimal;

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

@Entity
@Table(name = "contract_config")
public class ContractConfig extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1210825812484948884L;

	@Expose
	private Long configId;

	@Expose
	private String itemName;

	@Expose
	private String itemSpec;

	@Expose
	private BigDecimal amount;

	@Expose
	private String notes;

	private Contract contract;

	public ContractConfig() {
	}

	public ContractConfig(Long in_configId) {
		this.setConfigId(in_configId);
	}

	@Id
	@GeneratedValue
	@Column(name = "configId", unique = true, nullable = false)
	public Long getConfigId() {
		return this.configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contractId")
	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	@Column(name = "itemName", nullable = false, length = 128)
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "itemSpec", nullable = false, length = 128)
	public String getItemSpec() {
		return this.itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	@Column(name = "amount", nullable = false, precision = 18)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "notes", length = 200)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Transient
	public Long getContractId() {
		return this.getContract() == null ? null : this.getContract().getContractId();
	}

	public void setContractId(Long aValue) {
		if (aValue == null) {
			this.contract = null;
		} else if (this.contract == null) {
			this.contract = new Contract(aValue);
			this.contract.setVersion(new Integer(0));
		} else {
			this.contract.setContractId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ContractConfig)) {
			return false;
		}
		ContractConfig rhs = (ContractConfig) object;
		return new EqualsBuilder().append(this.configId, rhs.configId).append(this.itemName, rhs.itemName)
				.append(this.itemSpec, rhs.itemSpec).append(this.amount, rhs.amount).append(this.notes, rhs.notes)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.configId).append(this.itemName)
				.append(this.itemSpec).append(this.amount).append(this.notes).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("configId", this.configId).append("itemName", this.itemName)
				.append("itemSpec", this.itemSpec).append("amount", this.amount).append("notes", this.notes).toString();
	}
}
