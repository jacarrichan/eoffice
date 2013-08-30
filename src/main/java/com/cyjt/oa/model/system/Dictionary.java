package com.cyjt.oa.model.system;

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
@Table(name = "dictionary")
public class Dictionary extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4721399922291378629L;

	@Expose
	private Long dicId;

	@Expose
	private String itemName;

	@Expose
	private String itemValue;

	@Expose
	private String descp;

	@Expose
	private Short sn;

	@Expose
	private GlobalType globalType;

	@Expose
	private Long proTypeId;

	@Transient
	public Long getProTypeId() {
		return this.proTypeId;
	}

	public void setProTypeId(Long aValue) {
		if (aValue == null) {
			this.globalType = null;
		} else if (this.globalType == null) {
			this.globalType = new GlobalType(aValue);
		} else {
			this.globalType.setProTypeId(aValue);
		}
	}

	public Dictionary() {
	}

	public Dictionary(Long in_dicId) {
		this.setDicId(in_dicId);
	}

	@Id
	@GeneratedValue
	@Column(name = "dicId", unique = true, nullable = false)
	public Long getDicId() {
		return this.dicId;
	}

	public void setDicId(Long dicId) {
		this.dicId = dicId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proTypeId")
	public GlobalType getGlobalType() {
		return this.globalType;
	}

	public void setGlobalType(GlobalType globalType) {
		this.globalType = globalType;
	}

	@Column(name = "itemName", nullable = false, length = 64)
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "itemValue", nullable = false, length = 128)
	public String getItemValue() {
		return this.itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	@Column(name = "sn", nullable = false)
	public Short getSn() {
		return this.sn;
	}

	public void setSn(Short sn) {
		this.sn = sn;
	}

	@Column(name = "descp", length = 256)
	public String getDescp() {
		return this.descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Dictionary)) {
			return false;
		}
		Dictionary rhs = (Dictionary) object;
		return new EqualsBuilder().append(this.dicId, rhs.dicId).append(this.itemName, rhs.itemName)
				.append(this.itemValue, rhs.itemValue).append(this.descp, rhs.descp).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.dicId).append(this.itemName)
				.append(this.itemValue).append(this.descp).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("dicId", this.dicId).append("itemName", this.itemName)
				.append("itemValue", this.itemValue).append("descp", this.descp).toString();
	}
}
