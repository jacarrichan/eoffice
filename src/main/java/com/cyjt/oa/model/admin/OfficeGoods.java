package com.cyjt.oa.model.admin;

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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "office_goods")
public class OfficeGoods extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4345258715141798601L;

	@Expose
	private Long goodsId;

	@Expose
	private String goodsName;

	@Expose
	private String goodsNo;

	@Expose
	private String specifications;

	@Expose
	private String unit;

	@Expose
	private Short isWarning;

	@Expose
	private String notes;

	@Expose
	private Integer stockCounts;

	@Expose
	private Integer warnCounts;

	@Expose
	private OfficeGoodsType officeGoodsType;
	private Set<GoodsApply> goodsApplys = new HashSet<GoodsApply>();
	private Set<InStock> inStocks = new HashSet<InStock>();

	public OfficeGoods() {
	}

	public OfficeGoods(Long in_goodsId) {
		this.setGoodsId(in_goodsId);
	}
	@Transient
	public Long getTypeId() {
		return this.getOfficeGoodsType() == null ? null : this.getOfficeGoodsType().getTypeId();
	}

	public void setTypeId(Long aValue) {
		if (aValue == null) {
			this.officeGoodsType = null;
		} else if (this.officeGoodsType == null) {
			this.officeGoodsType = new OfficeGoodsType(aValue);
			this.officeGoodsType.setVersion(new Integer(0));
		} else {
			this.officeGoodsType.setTypeId(aValue);
		}
	}

	@Id
	@GeneratedValue
	@Column(name = "goodsId", unique = true, nullable = false)
	public Long getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId")
	public OfficeGoodsType getOfficeGoodsType() {
		return this.officeGoodsType;
	}

	public void setOfficeGoodsType(OfficeGoodsType officeGoodsType) {
		this.officeGoodsType = officeGoodsType;
	}

	@Column(name = "goodsName", nullable = false, length = 128)
	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "goodsNo", nullable = false, length = 128)
	public String getGoodsNo() {
		return this.goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	@Column(name = "specifications", nullable = false, length = 256)
	public String getSpecifications() {
		return this.specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	@Column(name = "unit", nullable = false, length = 64)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "isWarning", nullable = false)
	public Short getIsWarning() {
		return this.isWarning;
	}

	public void setIsWarning(Short isWarning) {
		this.isWarning = isWarning;
	}

	@Column(name = "notes", length = 500)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "stockCounts", nullable = false)
	public Integer getStockCounts() {
		return this.stockCounts;
	}

	public void setStockCounts(Integer stockCounts) {
		this.stockCounts = stockCounts;
	}

	@Column(name = "warnCounts", nullable = false)
	public Integer getWarnCounts() {
		return this.warnCounts;
	}

	public void setWarnCounts(Integer warnCounts) {
		this.warnCounts = warnCounts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "officeGoods")
	public Set<InStock> getInStocks() {
		return this.inStocks;
	}

	public void setInStocks(Set<InStock> inStocks) {
		this.inStocks = inStocks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "officeGoods")
	public Set<GoodsApply> getGoodsApplys() {
		return this.goodsApplys;
	}

	public void setGoodsApplys(Set<GoodsApply> goodsApplies) {
		this.goodsApplys = goodsApplies;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof OfficeGoods)) {
			return false;
		}
		OfficeGoods rhs = (OfficeGoods) object;
		return new EqualsBuilder().append(this.goodsId, rhs.goodsId).append(this.goodsName, rhs.goodsName)
				.append(this.goodsNo, rhs.goodsNo).append(this.specifications, rhs.specifications)
				.append(this.unit, rhs.unit).append(this.isWarning, rhs.isWarning).append(this.notes, rhs.notes)
				.append(this.stockCounts, rhs.stockCounts).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.goodsId).append(this.goodsName)
				.append(this.goodsNo).append(this.specifications).append(this.unit).append(this.isWarning)
				.append(this.notes).append(this.stockCounts).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("goodsId", this.goodsId).append("goodsName", this.goodsName)
				.append("goodsNo", this.goodsNo).append("specifications", this.specifications)
				.append("unit", this.unit).append("isWarning", this.isWarning).append("notes", this.notes)
				.append("stockCounts", this.stockCounts).toString();
	}
}
