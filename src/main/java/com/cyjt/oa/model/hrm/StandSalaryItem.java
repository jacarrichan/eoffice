package com.cyjt.oa.model.hrm;

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

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "stand_salary_item")
public class StandSalaryItem extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7705850661923235742L;
	private Long itemId;
	private String itemName;
	private BigDecimal amount;
	private Long salaryItemId;
	private StandSalary standSalary;

	public StandSalaryItem() {
	}

	public StandSalaryItem(Long in_itemId) {
		this.setItemId(in_itemId);
	}

	@Transient
	public Long getStandardId() {
		return this.getStandSalary() == null ? null : this.getStandSalary()
				.getStandardId();
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "itemId", unique = true, nullable = false)
	public Long getItemId() {
		return this.itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "standardId")
	public StandSalary getStandSalary() {
		return this.standSalary;
	}

	public void setStandSalary(StandSalary standSalary) {
		this.standSalary = standSalary;
	}

	@Column(name = "itemName", nullable = false, length = 64)
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "amount", nullable = false, precision = 18)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "salaryItemId")
	public Long getSalaryItemId() {
		return this.salaryItemId;
	}

	public void setSalaryItemId(Long salaryItemId) {
		this.salaryItemId = salaryItemId;
	}

	public void setStandardId(Long aValue) {
		if (aValue == null) {
			this.standSalary = null;
		} else if (this.standSalary == null) {
			this.standSalary = new StandSalary(aValue);
			this.standSalary.setVersion(new Integer(0));
		} else {
			this.standSalary.setStandardId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof StandSalaryItem)) {
			return false;
		}
		StandSalaryItem rhs = (StandSalaryItem) object;
		return new EqualsBuilder().append(this.itemId, rhs.itemId)
				.append(this.itemName, rhs.itemName)
				.append(this.amount, rhs.amount)
				.append(this.salaryItemId, rhs.salaryItemId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.itemId)
				.append(this.itemName).append(this.amount)
				.append(this.salaryItemId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("itemId", this.itemId)
				.append("itemName", this.itemName)
				.append("amount", this.amount)
				.append("salaryItemId", this.salaryItemId).toString();
	}
}
