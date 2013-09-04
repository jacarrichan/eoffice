package com.palmelf.eoffice.model.admin;

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

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "in_stock")
public class InStock extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6582500698392278091L;
	private Long buyId;
	private String providerName;
	private String stockNo;
	private BigDecimal price;
	private Integer inCounts;
	private BigDecimal amount;
	private Date inDate;
	private String buyer;
	private OfficeGoods officeGoods;

	public InStock() {
	}

	public InStock(Long in_buyId) {
		this.setBuyId(in_buyId);
	}
	@Transient
	public Long getGoodsId() {
		return this.getOfficeGoods() == null ? null : this.getOfficeGoods().getGoodsId();
	}

	public void setGoodsId(Long aValue) {
		if (aValue == null) {
			this.officeGoods = null;
		} else if (this.officeGoods == null) {
			this.officeGoods = new OfficeGoods(aValue);
			this.officeGoods.setVersion(new Integer(0));
		} else {
			this.officeGoods.setGoodsId(aValue);
		}
	}

	@Id
	@GeneratedValue
	@Column(name = "buyId", unique = true, nullable = false)
	public Long getBuyId() {
		return this.buyId;
	}

	public void setBuyId(Long buyId) {
		this.buyId = buyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsId")
	public OfficeGoods getOfficeGoods() {
		return this.officeGoods;
	}

	public void setOfficeGoods(OfficeGoods officeGoods) {
		this.officeGoods = officeGoods;
	}

	@Column(name = "providerName", length = 128)
	public String getProviderName() {
		return this.providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	@Column(name = "stockNo", nullable = false, length = 128)
	public String getStockNo() {
		return this.stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	@Column(name = "price", precision = 18)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "inCounts")
	public Integer getInCounts() {
		return this.inCounts;
	}

	public void setInCounts(Integer inCounts) {
		this.inCounts = inCounts;
	}

	@Column(name = "amount", nullable = false, precision = 18)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "inDate", nullable = false, length = 19)
	public Date getInDate() {
		return this.inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	@Column(name = "buyer", length = 128)
	public String getBuyer() {
		return this.buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof InStock)) {
			return false;
		}
		InStock rhs = (InStock) object;
		return new EqualsBuilder().append(this.buyId, rhs.buyId).append(this.providerName, rhs.providerName)
				.append(this.stockNo, rhs.stockNo).append(this.price, rhs.price).append(this.inCounts, rhs.inCounts)
				.append(this.amount, rhs.amount).append(this.inDate, rhs.inDate).append(this.buyer, rhs.buyer)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.buyId).append(this.providerName)
				.append(this.stockNo).append(this.price).append(this.inCounts).append(this.amount).append(this.inDate)
				.append(this.buyer).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("buyId", this.buyId).append("providerName", this.providerName)
				.append("stockNo", this.stockNo).append("price", this.price).append("inCounts", this.inCounts)
				.append("amount", this.amount).append("inDate", this.inDate).append("buyer", this.buyer).toString();
	}
}
