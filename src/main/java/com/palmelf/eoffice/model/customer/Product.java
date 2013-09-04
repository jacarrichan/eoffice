package com.palmelf.eoffice.model.customer;

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
@Table(name = "product")
public class Product extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2353704490999622547L;
	private Long productId;
	private String productName;
	private String productModel;
	private String unit;
	private BigDecimal costPrice;
	private BigDecimal salesPrice;
	private String productDesc;
	private Date createtime;
	private Date updatetime;
	private Provider provider;

	public Product() {
	}

	public Product(Long in_productId) {
		this.setProductId(in_productId);
	}

	@Id
	@GeneratedValue
	@Column(name = "productId", unique = true, nullable = false)
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "providerId")
	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@Column(name = "productName", nullable = false, length = 128)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "productModel", length = 128)
	public String getProductModel() {
		return this.productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	@Column(name = "unit", length = 128)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "costPrice", precision = 18)
	public BigDecimal getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	@Column(name = "salesPrice", precision = 18)
	public BigDecimal getSalesPrice() {
		return this.salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	@Column(name = "productDesc", length = 512)
	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "updatetime", nullable = false, length = 19)
	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	@Transient
	public Long getProviderId() {
		return this.getProvider() == null ? null : this.getProvider().getProviderId();
	}

	public void setProviderId(Long aValue) {
		if (aValue == null) {
			this.provider = null;
		} else if (this.provider == null) {
			this.provider = new Provider(aValue);
			this.provider.setVersion(new Integer(0));
		} else {
			this.provider.setProviderId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Product)) {
			return false;
		}
		Product rhs = (Product) object;
		return new EqualsBuilder().append(this.productId, rhs.productId).append(this.productName, rhs.productName)
				.append(this.productModel, rhs.productModel).append(this.unit, rhs.unit)
				.append(this.costPrice, rhs.costPrice).append(this.salesPrice, rhs.salesPrice)
				.append(this.productDesc, rhs.productDesc).append(this.createtime, rhs.createtime)
				.append(this.updatetime, rhs.updatetime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.productId).append(this.productName)
				.append(this.productModel).append(this.unit).append(this.costPrice).append(this.salesPrice)
				.append(this.productDesc).append(this.createtime).append(this.updatetime).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("productId", this.productId).append("productName", this.productName)
				.append("productModel", this.productModel).append("unit", this.unit)
				.append("costPrice", this.costPrice).append("salesPrice", this.salesPrice)
				.append("productDesc", this.productDesc).append("createtime", this.createtime)
				.append("updatetime", this.updatetime).toString();
	}
}
