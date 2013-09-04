package com.palmelf.eoffice.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "board_type")
public class BoardType extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 461217558995902828L;
	private Long typeId;
	private String typeName;
	private String typeDesc;

	public BoardType() {
	}

	public BoardType(Long in_typeId) {
		this.setTypeId(in_typeId);
	}

	@Id
	@GeneratedValue
	@Column(name = "typeId", unique = true, nullable = false)
	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@Column(name = "typeName", nullable = false, length = 128)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "typeDesc", length = 4000)
	public String getTypeDesc() {
		return this.typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BoardType)) {
			return false;
		}
		BoardType rhs = (BoardType) obj;
		return new EqualsBuilder().append(this.typeId, rhs.typeId).append(this.typeName, rhs.typeName)
				.append(this.typeDesc, rhs.typeDesc).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.typeId).append(this.typeName)
				.append(this.typeDesc).hashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("typeId", this.typeId).append("typeName", this.typeName)
				.append("typeDesc", this.typeDesc).toString();
	}
}
