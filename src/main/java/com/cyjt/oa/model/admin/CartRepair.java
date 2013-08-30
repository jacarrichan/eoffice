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
@Table(name = "cart_repair")
public class CartRepair extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6350011579992700834L;
	private Long repairId;
	private Date repairDate;
	private String reason;
	private String executant;
	private String notes;
	private String repairType;
	private BigDecimal fee;
	private Car car;

	public CartRepair() {
	}

	public CartRepair(Long in_repairId) {
		this.setRepairId(in_repairId);
	}
	@Transient
	public Long getCarId() {
		return this.getCar() == null ? null : this.getCar().getCarId();
	}

	public void setCarId(Long aValue) {
		if (aValue == null) {
			this.car = null;
		} else if (this.car == null) {
			this.car = new Car(aValue);
			this.car.setVersion(new Integer(0));
		} else {
			this.car.setCarId(aValue);
		}
	}

	@Id
	@GeneratedValue
	@Column(name = "repairId", unique = true, nullable = false)
	public Long getRepairId() {
		return this.repairId;
	}

	public void setRepairId(Long repairId) {
		this.repairId = repairId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "carId")
	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Column(name = "repairDate", nullable = false, length = 19)
	public Date getRepairDate() {
		return this.repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}

	@Column(name = "reason", nullable = false, length = 128)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "executant", nullable = false, length = 128)
	public String getExecutant() {
		return this.executant;
	}

	public void setExecutant(String executant) {
		this.executant = executant;
	}

	@Column(name = "notes", length = 128)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "repairType", nullable = false, length = 128)
	public String getRepairType() {
		return this.repairType;
	}

	public void setRepairType(String repairType) {
		this.repairType = repairType;
	}

	@Column(name = "fee", precision = 18)
	public BigDecimal getFee() {
		return this.fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CartRepair)) {
			return false;
		}
		CartRepair rhs = (CartRepair) object;
		return new EqualsBuilder().append(this.repairId, rhs.repairId).append(this.repairDate, rhs.repairDate)
				.append(this.reason, rhs.reason).append(this.executant, rhs.executant).append(this.notes, rhs.notes)
				.append(this.repairType, rhs.repairType).append(this.fee, rhs.fee).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.repairId).append(this.repairDate)
				.append(this.reason).append(this.executant).append(this.notes).append(this.repairType).append(this.fee)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("repairId", this.repairId).append("repairDate", this.repairDate)
				.append("reason", this.reason).append("executant", this.executant).append("notes", this.notes)
				.append("repairType", this.repairType).append("fee", this.fee).toString();
	}
}
