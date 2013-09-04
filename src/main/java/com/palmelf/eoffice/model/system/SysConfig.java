package com.palmelf.eoffice.model.system;

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
@Table(name = "sys_config")
public class SysConfig extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3679212214644738401L;
	public static final String CODE_OPEN = "1";
	public static final String CODE_COLSE = "2";
	public static final String SMS_OPEN = "1";
	public static final String SMS_COLSE = "2";
	private Long configId;
	private String configKey;
	private String configName;
	private String configDesc;
	private String typeName;
	private Short dataType;
	private String dataValue;
	private String typeKey;

	public SysConfig() {
	}

	public SysConfig(Long in_configId) {
		this.setConfigId(in_configId);
	}

	@Id
	@GeneratedValue
	@Column(name = "configId", unique = true, nullable = false)
	public Long getConfigId() {
		return this.configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	@Column(name = "configKey", nullable = false, length = 64)
	public String getConfigKey() {
		return this.configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	@Column(name = "configName", nullable = false, length = 64)
	public String getConfigName() {
		return this.configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	@Column(name = "configDesc", length = 256)
	public String getConfigDesc() {
		return this.configDesc;
	}

	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}

	@Column(name = "typeName", nullable = false, length = 32)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "dataType", nullable = false)
	public Short getDataType() {
		return this.dataType;
	}

	public void setDataType(Short dataType) {
		this.dataType = dataType;
	}

	@Column(name = "dataValue", length = 64)
	public String getDataValue() {
		return this.dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	@Column(name = "typeKey", length = 64)
	public String getTypeKey() {
		return this.typeKey;
	}

	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SysConfig)) {
			return false;
		}
		SysConfig rhs = (SysConfig) object;
		return new EqualsBuilder().append(this.configId, rhs.configId).append(this.configKey, rhs.configKey)
				.append(this.configName, rhs.configName).append(this.configDesc, rhs.configDesc)
				.append(this.typeName, rhs.typeName).append(this.dataType, rhs.dataType)
				.append(this.typeKey, rhs.typeKey).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.configId).append(this.configKey)
				.append(this.configName).append(this.configDesc).append(this.typeName).append(this.dataType)
				.append(this.typeKey).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("configId", this.configId).append("configKey", this.configKey)
				.append("configName", this.configName).append("configDesc", this.configDesc)
				.append("typeName", this.typeName).append("dataType", this.dataType).append("typeKey", this.typeKey)
				.toString();
	}
}
