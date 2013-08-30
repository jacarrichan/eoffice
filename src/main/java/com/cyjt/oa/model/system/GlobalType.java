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
@Table(name = "global_type")
public class GlobalType extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1820004349799318821L;
	public static final String CAT_ARCH_FOND = "AR_FD";
	public static final String CAT_ARCH_ROLL = "AR_RL";
	public static final String CAT_ROLL_FILE = "AR_RLF";
	private Long proTypeId;
	private String typeName;
	private String path;
	private Integer depth;
	private Long parentId;
	private String nodeKey;
	private String catKey;
	private Integer sn;

	public GlobalType() {
	}

	public GlobalType(Long in_proTypeId) {
		this.setProTypeId(in_proTypeId);
	}

	@Id
	@GeneratedValue
	@Column(name = "proTypeId", unique = true, nullable = false)
	public Long getProTypeId() {
		return this.proTypeId;
	}

	public void setProTypeId(Long proTypeId) {
		this.proTypeId = proTypeId;
	}

	@Column(name = "typeName", nullable = false, length = 128)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "path", length = 64)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "depth", nullable = false)
	public Integer getDepth() {
		return this.depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	@Column(name = "parentId")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "nodeKey", nullable = false, length = 64)
	public String getNodeKey() {
		return this.nodeKey;
	}

	public void setNodeKey(String nodeKey) {
		this.nodeKey = nodeKey;
	}

	@Column(name = "catKey", nullable = false, length = 64)
	public String getCatKey() {
		return this.catKey;
	}

	public void setCatKey(String catKey) {
		this.catKey = catKey;
	}

	@Column(name = "sn", nullable = false)
	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof GlobalType)) {
			return false;
		}
		GlobalType rhs = (GlobalType) object;
		return new EqualsBuilder().append(this.proTypeId, rhs.proTypeId).append(this.typeName, rhs.typeName)
				.append(this.path, rhs.path).append(this.depth, rhs.depth).append(this.parentId, rhs.parentId)
				.append(this.nodeKey, rhs.nodeKey).append(this.catKey, rhs.catKey).append(this.sn, rhs.sn).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.proTypeId).append(this.typeName)
				.append(this.path).append(this.depth).append(this.parentId).append(this.nodeKey).append(this.catKey)
				.append(this.sn).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("proTypeId", this.proTypeId).append("typeName", this.typeName)
				.append("path", this.path).append("depth", this.depth).append("parentId", this.parentId)
				.append("nodeKey", this.nodeKey).append("catKey", this.catKey).append("sn", this.sn).toString();
	}
}
