package com.palmelf.eoffice.model.admin;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "fixed_assets")
public class FixedAssets extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6760020155025143275L;
	private Long assetsId;
	private String assetsNo;
	private String assetsName;
	private String model;
	private String manufacturer;
	private Date manuDate;
	private Date buyDate;
	private String beDep;
	private String custodian;
	private String notes;
	private BigDecimal remainValRate;
	private Date startDepre;
	private BigDecimal intendTerm;
	private BigDecimal intendWorkGross;
	private String workGrossUnit;
	private BigDecimal assetValue;
	private BigDecimal assetCurValue;
	private BigDecimal depreRate;
	private BigDecimal defPerWorkGross;
	private DepreType depreType;
	private AssetsType assetsType;
	private Set<DepreRecord> depreRecords = new HashSet<DepreRecord>();

	public FixedAssets() {
	}

	public FixedAssets(Long in_assetsId) {
		this.setAssetsId(in_assetsId);
	}

	public Long getAssetsTypeId() {
		return this.getAssetsType() == null ? null : this.getAssetsType().getAssetsTypeId();
	}

	public void setAssetsTypeId(Long aValue) {
		if (aValue == null) {
			this.assetsType = null;
		} else if (this.assetsType == null) {
			this.assetsType = new AssetsType(aValue);
			this.assetsType.setVersion(new Integer(0));
		} else {
			this.assetsType.setAssetsTypeId(aValue);
		}
	}

	@Id
	@GeneratedValue
	@Column(name = "assetsId", unique = true, nullable = false)
	public Long getAssetsId() {
		return this.assetsId;
	}

	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assetsTypeId", insertable=false,updatable=false)
	public AssetsType getAssetsType() {
		return this.assetsType;
	}

	public void setAssetsType(AssetsType assetsType) {
		this.assetsType = assetsType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depreTypeId", insertable=false,updatable=false)
	public DepreType getDepreType() {
		return this.depreType;
	}

	public void setDepreType(DepreType depreType) {
		this.depreType = depreType;
	}

	@Column(name = "assetsNo", length = 128)
	public String getAssetsNo() {
		return this.assetsNo;
	}

	public void setAssetsNo(String assetsNo) {
		this.assetsNo = assetsNo;
	}

	@Column(name = "assetsName", nullable = false, length = 128)
	public String getAssetsName() {
		return this.assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	@Column(name = "model", length = 64)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "manufacturer", length = 64)
	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "manuDate", length = 19)
	public Date getManuDate() {
		return this.manuDate;
	}

	public void setManuDate(Date manuDate) {
		this.manuDate = manuDate;
	}

	@Column(name = "buyDate", nullable = false, length = 19)
	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	@Column(name = "beDep", nullable = false, length = 64)
	public String getBeDep() {
		return this.beDep;
	}

	public void setBeDep(String beDep) {
		this.beDep = beDep;
	}

	@Column(name = "custodian", length = 32)
	public String getCustodian() {
		return this.custodian;
	}

	public void setCustodian(String custodian) {
		this.custodian = custodian;
	}

	@Column(name = "notes", length = 500)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "remainValRate", nullable = false, precision = 18, scale = 6)
	public BigDecimal getRemainValRate() {
		return this.remainValRate;
	}

	public void setRemainValRate(BigDecimal remainValRate) {
		this.remainValRate = remainValRate;
	}

	@Column(name = "startDepre", length = 19)
	public Date getStartDepre() {
		return this.startDepre;
	}

	public void setStartDepre(Date startDepre) {
		this.startDepre = startDepre;
	}

	@Column(name = "intendTerm", precision = 18)
	public BigDecimal getIntendTerm() {
		return this.intendTerm;
	}

	public void setIntendTerm(BigDecimal intendTerm) {
		this.intendTerm = intendTerm;
	}

	@Column(name = "intendWorkGross", precision = 18)
	public BigDecimal getIntendWorkGross() {
		return this.intendWorkGross;
	}

	public void setIntendWorkGross(BigDecimal intendWorkGross) {
		this.intendWorkGross = intendWorkGross;
	}

	@Column(name = "workGrossUnit", length = 128)
	public String getWorkGrossUnit() {
		return this.workGrossUnit;
	}

	public void setWorkGrossUnit(String workGrossUnit) {
		this.workGrossUnit = workGrossUnit;
	}

	@Column(name = "assetValue", nullable = false, precision = 18, scale = 4)
	public BigDecimal getAssetValue() {
		return this.assetValue;
	}

	public void setAssetValue(BigDecimal assetValue) {
		this.assetValue = assetValue;
	}

	@Column(name = "assetCurValue", nullable = false, precision = 18, scale = 4)
	public BigDecimal getAssetCurValue() {
		return this.assetCurValue;
	}

	public void setAssetCurValue(BigDecimal assetCurValue) {
		this.assetCurValue = assetCurValue;
	}

	@Column(name = "depreRate", precision = 18)
	public BigDecimal getDepreRate() {
		return this.depreRate;
	}

	public void setDepreRate(BigDecimal depreRate) {
		this.depreRate = depreRate;
	}

	@Column(name = "defPerWorkGross", precision = 18)
	public BigDecimal getDefPerWorkGross() {
		return this.defPerWorkGross;
	}

	public void setDefPerWorkGross(BigDecimal defPerWorkGross) {
		this.defPerWorkGross = defPerWorkGross;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fixedAssets")
	public Set<DepreRecord> getDepreRecords() {
		return this.depreRecords;
	}

	public void setDepreRecords(Set<DepreRecord> depreRecords) {
		this.depreRecords = depreRecords;
	}

	public Long getDepreTypeId() {
		return this.getDepreType() == null ? null : this.getDepreType().getDepreTypeId();
	}

	public void setDepreTypeId(Long aValue) {
		if (aValue == null) {
			this.depreType = null;
		} else if (this.depreType == null) {
			this.depreType = new DepreType(aValue);
			this.depreType.setVersion(new Integer(0));
		} else {
			this.depreType.setDepreTypeId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof FixedAssets)) {
			return false;
		}
		FixedAssets rhs = (FixedAssets) object;
		return new EqualsBuilder().append(this.assetsId, rhs.assetsId).append(this.assetsNo, rhs.assetsNo)
				.append(this.assetsName, rhs.assetsName).append(this.model, rhs.model)
				.append(this.manufacturer, rhs.manufacturer).append(this.manuDate, rhs.manuDate)
				.append(this.buyDate, rhs.buyDate).append(this.beDep, rhs.beDep).append(this.custodian, rhs.custodian)
				.append(this.notes, rhs.notes).append(this.depreRate, rhs.depreRate)
				.append(this.remainValRate, rhs.remainValRate).append(this.startDepre, rhs.startDepre)
				.append(this.intendTerm, rhs.intendTerm).append(this.intendWorkGross, rhs.intendWorkGross)
				.append(this.workGrossUnit, rhs.workGrossUnit).append(this.assetValue, rhs.assetValue)
				.append(this.assetCurValue, rhs.assetCurValue).append(this.defPerWorkGross, rhs.defPerWorkGross)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.assetsId).append(this.assetsNo)
				.append(this.assetsName).append(this.model).append(this.manufacturer).append(this.manuDate)
				.append(this.buyDate).append(this.beDep).append(this.custodian).append(this.notes)
				.append(this.depreRate).append(this.remainValRate).append(this.startDepre).append(this.intendTerm)
				.append(this.intendWorkGross).append(this.workGrossUnit).append(this.assetValue)
				.append(this.defPerWorkGross).append(this.assetCurValue).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("assetsId", this.assetsId).append("assetsNo", this.assetsNo)
				.append("assetsName", this.assetsName).append("model", this.model)
				.append("manufacturer", this.manufacturer).append("manuDate", this.manuDate)
				.append("buyDate", this.buyDate).append("beDep", this.beDep).append("custodian", this.custodian)
				.append("notes", this.notes).append("remainValRate", this.remainValRate)
				.append("startDepre", this.startDepre).append("intendTerm", this.intendTerm)
				.append("intendWorkGross", this.intendWorkGross).append("workGrossUnit", this.workGrossUnit)
				.append("assetValue", this.assetValue).append("depreRate", this.depreRate)
				.append("defPerWorkGross", this.defPerWorkGross).append("assetCurValue", this.assetCurValue).toString();
	}
}
