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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "depre_record")
public class DepreRecord extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8045271880856984466L;
	private Long recordId;
	private BigDecimal workCapacity;
	private BigDecimal depreAmount;
	private Date calTime;
	private FixedAssets fixedAssets;

	public DepreRecord() {
	}

	public DepreRecord(Long in_recordId) {
		this.setRecordId(in_recordId);
	}

	@Transient
	public Long getAssetsId() {
		return this.getFixedAssets() == null ? null : this.getFixedAssets().getAssetsId();
	}

	public void setAssetsId(Long aValue) {
		if (aValue == null) {
			this.fixedAssets = null;
		} else if (this.fixedAssets == null) {
			this.fixedAssets = new FixedAssets(aValue);
			this.fixedAssets.setVersion(new Integer(0));
		} else {
			this.fixedAssets.setAssetsId(aValue);
		}
	}

	@Id
	@GeneratedValue
	@Column(name = "recordId", unique = true, nullable = false)
	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assetsId")
	public FixedAssets getFixedAssets() {
		return this.fixedAssets;
	}

	public void setFixedAssets(FixedAssets fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	@Column(name = "workCapacity", precision = 18)
	public BigDecimal getWorkCapacity() {
		return this.workCapacity;
	}

	public void setWorkCapacity(BigDecimal workCapacity) {
		this.workCapacity = workCapacity;
	}

	@Column(name = "depreAmount", nullable = false, precision = 18, scale = 4)
	public BigDecimal getDepreAmount() {
		return this.depreAmount;
	}

	public void setDepreAmount(BigDecimal depreAmount) {
		this.depreAmount = depreAmount;
	}

	@Column(name = "calTime", nullable = false, length = 19)
	public Date getCalTime() {
		return this.calTime;
	}

	public void setCalTime(Date calTime) {
		this.calTime = calTime;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DepreRecord)) {
			return false;
		}
		DepreRecord rhs = (DepreRecord) object;
		return new EqualsBuilder().append(this.recordId, rhs.recordId).append(this.workCapacity, rhs.workCapacity)
				.append(this.depreAmount, rhs.depreAmount).append(this.calTime, rhs.calTime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.recordId).append(this.workCapacity)
				.append(this.depreAmount).append(this.calTime).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("recordId", this.recordId).append("workCapacity", this.workCapacity)
				.append("depreAmount", this.depreAmount).append("calTime", this.calTime).toString();
	}
}
