package com.cyjt.oa.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "assets_type")
public class AssetsType extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "assetsTypeId", unique = true, nullable = false)
	private Long assetsTypeId;
	@Column(name = "typeName", nullable = false)
	private String typeName;

	public AssetsType() {
	}

	public AssetsType(Long in_assetsTypeId) {
		this.setAssetsTypeId(in_assetsTypeId);
	}

	public Long getAssetsTypeId() {
		return this.assetsTypeId;
	}

	public void setAssetsTypeId(Long aValue) {
		this.assetsTypeId = aValue;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String aValue) {
		this.typeName = aValue;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AssetsType)) {
			return false;
		}
		AssetsType rhs = (AssetsType) object;
		return new EqualsBuilder().append(this.assetsTypeId, rhs.assetsTypeId).append(this.typeName, rhs.typeName)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.assetsTypeId).append(this.typeName).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("assetsTypeId", this.assetsTypeId).append("typeName", this.typeName)
				.toString();
	}
}
