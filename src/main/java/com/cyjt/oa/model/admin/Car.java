package com.cyjt.oa.model.admin;

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
@Table(name = "car")
public class Car extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6544269270550787460L;
	public static short PASS_APPLY = 1;
	public static short NOTPASS_APPLY = 0;

	@Expose
	private Long carId;

	@Expose
	private String carNo;

	@Expose
	private String carType;

	@Expose
	private String engineNo;

	@Expose
	private Date buyInsureTime;

	@Expose
	private Date auditTime;

	@Expose
	private String notes;

	@Expose
	private String factoryModel;

	@Expose
	private String driver;

	@Expose
	private Date buyDate;

	@Expose
	private Short status;

	@Expose
	private String cartImage;
	private Set<CarApply> carApplys = new HashSet<CarApply>();
	private Set<CartRepair> cartRepairs = new HashSet<CartRepair>();

	public Car() {
	}

	public Car(Long in_carId) {
		this.setCarId(in_carId);
	}

	@Id
	@GeneratedValue
	@Column(name = "carId", unique = true, nullable = false)
	public Long getCarId() {
		return this.carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	@Column(name = "carNo", nullable = false, length = 128)
	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "carType", nullable = false, length = 64)
	public String getCarType() {
		return this.carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	@Column(name = "engineNo", length = 128)
	public String getEngineNo() {
		return this.engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	@Column(name = "buyInsureTime", length = 19)
	public Date getBuyInsureTime() {
		return this.buyInsureTime;
	}

	public void setBuyInsureTime(Date buyInsureTime) {
		this.buyInsureTime = buyInsureTime;
	}

	@Column(name = "auditTime", length = 19)
	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	@Column(name = "notes", length = 500)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "factoryModel", length = 64)
	public String getFactoryModel() {
		return this.factoryModel;
	}

	public void setFactoryModel(String factoryModel) {
		this.factoryModel = factoryModel;
	}

	@Column(name = "driver", length = 32)
	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	@Column(name = "buyDate", length = 19)
	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "cartImage", length = 128)
	public String getCartImage() {
		return this.cartImage;
	}

	public void setCartImage(String cartImage) {
		this.cartImage = cartImage;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "car")
	public Set<CartRepair> getCartRepairs() {
		return this.cartRepairs;
	}

	public void setCartRepairs(Set<CartRepair> cartRepairs) {
		this.cartRepairs = cartRepairs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "car")
	public Set<CarApply> getCarApplies() {
		return this.carApplys;
	}

	public void setCarApplies(Set<CarApply> carApplies) {
		this.carApplys = carApplies;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Car)) {
			return false;
		}
		Car rhs = (Car) object;
		return new EqualsBuilder().append(this.carId, rhs.carId).append(this.carNo, rhs.carNo)
				.append(this.carType, rhs.carType).append(this.engineNo, rhs.engineNo)
				.append(this.buyInsureTime, rhs.buyInsureTime).append(this.auditTime, rhs.auditTime)
				.append(this.notes, rhs.notes).append(this.factoryModel, rhs.factoryModel)
				.append(this.driver, rhs.driver).append(this.buyDate, rhs.buyDate).append(this.status, rhs.status)
				.append(this.cartImage, rhs.cartImage).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.carId).append(this.carNo).append(this.carType)
				.append(this.engineNo).append(this.buyInsureTime).append(this.auditTime).append(this.notes)
				.append(this.factoryModel).append(this.driver).append(this.buyDate).append(this.status)
				.append(this.cartImage).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("carId", this.carId).append("carNo", this.carNo)
				.append("carType", this.carType).append("engineNo", this.engineNo)
				.append("buyInsureTime", this.buyInsureTime).append("auditTime", this.auditTime)
				.append("notes", this.notes).append("factoryModel", this.factoryModel).append("driver", this.driver)
				.append("buyDate", this.buyDate).append("status", this.status).append("cartImage", this.cartImage)
				.toString();
	}
}
