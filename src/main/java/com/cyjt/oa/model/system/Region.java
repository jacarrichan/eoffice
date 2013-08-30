package com.cyjt.oa.model.system;

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
@Table(name = "region")
public class Region extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -381494055876099156L;
	private Long regionId;
	private String regionName;
	private Short regionType;
	private Long parentId;

	public Region() {
	}

	public Region(Long in_regionId) {
		this.setRegionId(in_regionId);
	}

	@Id
	@GeneratedValue
	@Column(name = "regionId", unique = true, nullable = false)
	public Long getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	@Column(name = "regionName", nullable = false, length = 128)
	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Column(name = "regionType", nullable = false)
	public Short getRegionType() {
		return this.regionType;
	}

	public void setRegionType(Short regionType) {
		this.regionType = regionType;
	}

	@Column(name = "parentId")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Region)) {
			return false;
		}
		Region rhs = (Region) object;
		return new EqualsBuilder().append(this.regionId, rhs.regionId).append(this.regionName, rhs.regionName)
				.append(this.regionType, rhs.regionType).append(this.parentId, rhs.parentId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.regionId).append(this.regionName)
				.append(this.regionType).append(this.parentId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("regionId", this.regionId).append("regionName", this.regionName)
				.append("regionType", this.regionType).append("parentId", this.parentId).toString();
	}
}
