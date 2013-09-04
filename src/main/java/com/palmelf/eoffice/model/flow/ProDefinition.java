package com.palmelf.eoffice.model.flow;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;

import flexjson.JSON;

@Entity
@Table(name = "pro_definition")
public class ProDefinition extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3527865212351967953L;
	public static final Short IS_DEFAULT = 1;
	public static final Short IS_NOT_DEFAULT = 0;
	private Long defId;
	private String name;
	private String description;
	private Date createtime;
	private String deployId;
	private String defXml;
	private String drawDefXml;
	private Short isDefault = ProDefinition.IS_NOT_DEFAULT;
	private ProType proType;

	public ProDefinition() {
	}

	public ProDefinition(Long in_defId) {
		this.setDefId(in_defId);
	}

	@Id
	@GeneratedValue
	@Column(name = "defId", unique = true, nullable = false)
	public Long getDefId() {
		return this.defId;
	}

	public void setDefId(Long defId) {
		this.defId = defId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId")
	public ProType getProType() {
		return this.proType;
	}

	public void setProType(ProType proType) {
		this.proType = proType;
	}

	@Column(name = "name", nullable = false, length = 256)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 1024)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "deployId", nullable = false, length = 64)
	public String getDeployId() {
		return this.deployId;
	}

	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}

	@JSON
	@Lob
	@Column(name = "defXml", nullable = false)
	public String getDefXml() {
		return this.defXml;
	}

	public void setDefXml(String defXml) {
		this.defXml = defXml;
	}

	@Lob
	@Column(name = "drawDefXml")
	public String getDrawDefXml() {
		return this.drawDefXml;
	}

	public void setDrawDefXml(String drawDefXml) {
		this.drawDefXml = drawDefXml;
	}

	@Column(name = "isDefault", nullable = false)
	public Short getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}

	public void setProTypeId(Long proTypeId) {
		if (this.proType == null) {
			this.proType = new ProType();
		}
		this.proType.setTypeId(proTypeId);
	}

	@Transient
	public Long getProTypeId() {
		return this.proType == null ? null : this.proType.getTypeId();
	}

	@Transient
	public Long getTypeId() {
		return this.getProType() == null ? null : this.getProType().getTypeId();
	}

	public void setTypeId(Long aValue) {
		if (aValue == null) {
			this.proType = null;
		} else if (this.proType == null) {
			this.proType = new ProType(aValue);
		} else {
			this.proType.setTypeId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ProDefinition)) {
			return false;
		}
		ProDefinition rhs = (ProDefinition) object;
		return new EqualsBuilder().append(this.defId, rhs.defId)
				.append(this.name, rhs.name)
				.append(this.description, rhs.description)
				.append(this.createtime, rhs.createtime)
				.append(this.deployId, rhs.deployId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.defId)
				.append(this.name).append(this.description)
				.append(this.createtime).append(this.deployId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("defId", this.defId)
				.append("name", this.name)
				.append("description", this.description)
				.append("createtime", this.createtime)
				.append("deployId", this.deployId).toString();
	}

}
