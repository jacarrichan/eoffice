package com.palmelf.eoffice.model.hrm;

import java.math.BigDecimal;

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
@Table(name = "salary_item")
public class SalaryItem extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -142661426763839183L;
	private Long salaryItemId;
	private String itemName;
	private BigDecimal defaultVal;

	public SalaryItem() {
	}

	public SalaryItem(Long in_salaryItemId) {
		this.setSalaryItemId(in_salaryItemId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "salaryItemId", unique = true, nullable = false)
	public Long getSalaryItemId() {
		return this.salaryItemId;
	}

	public void setSalaryItemId(Long salaryItemId) {
		this.salaryItemId = salaryItemId;
	}

	@Column(name = "itemName", nullable = false, length = 128)
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "defaultVal", nullable = false, precision = 18)
	public BigDecimal getDefaultVal() {
		return this.defaultVal;
	}

	public void setDefaultVal(BigDecimal defaultVal) {
		this.defaultVal = defaultVal;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SalaryItem)) {
			return false;
		}
		SalaryItem rhs = (SalaryItem) object;
		return new EqualsBuilder().append(this.salaryItemId, rhs.salaryItemId).append(this.itemName, rhs.itemName)
				.append(this.defaultVal, rhs.defaultVal).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.salaryItemId).append(this.itemName)
				.append(this.defaultVal).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("salaryItemId", this.salaryItemId).append("itemName", this.itemName)
				.append("defaultVal", this.defaultVal).toString();
	}
}
