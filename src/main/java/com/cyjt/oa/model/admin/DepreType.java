package com.cyjt.oa.model.admin;

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

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "depre_type")
public class DepreType extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3901879177152528156L;
	private Long depreTypeId;
	private String typeName;
	private Integer deprePeriod;
	private String typeDesc;
	private Short calMethod;
	private Set<FixedAssets> fixedAssetses = new HashSet<FixedAssets>(0);

	public DepreType() {
	}

	public DepreType(Long in_depreTypeId) {
		this.setDepreTypeId(in_depreTypeId);
	}

	@Id
	@GeneratedValue
	@Column(name = "depreTypeId", unique = true, nullable = false)
	public Long getDepreTypeId() {
		return this.depreTypeId;
	}

	public void setDepreTypeId(Long depreTypeId) {
		this.depreTypeId = depreTypeId;
	}

	@Column(name = "typeName", nullable = false, length = 64)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "deprePeriod", nullable = false)
	public Integer getDeprePeriod() {
		return this.deprePeriod;
	}

	public void setDeprePeriod(Integer deprePeriod) {
		this.deprePeriod = deprePeriod;
	}

	@Column(name = "typeDesc", length = 500)
	public String getTypeDesc() {
		return this.typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	@Column(name = "calMethod", nullable = false)
	public Short getCalMethod() {
		return this.calMethod;
	}

	public void setCalMethod(Short calMethod) {
		this.calMethod = calMethod;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "depreType")
	public Set<FixedAssets> getFixedAssetses() {
		return this.fixedAssetses;
	}

	public void setFixedAssetses(Set<FixedAssets> fixedAssetses) {
		this.fixedAssetses = fixedAssetses;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DepreType)) {
			return false;
		}
		DepreType rhs = (DepreType) object;
		return new EqualsBuilder().append(this.depreTypeId, rhs.depreTypeId).append(this.typeName, rhs.typeName)
				.append(this.deprePeriod, rhs.deprePeriod).append(this.typeDesc, rhs.typeDesc)
				.append(this.calMethod, rhs.calMethod).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.depreTypeId).append(this.typeName)
				.append(this.deprePeriod).append(this.typeDesc).append(this.calMethod).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("depreTypeId", this.depreTypeId).append("typeName", this.typeName)
				.append("deprePeriod", this.deprePeriod).append("typeDesc", this.typeDesc)
				.append("calMethod", this.calMethod).toString();
	}
}
