package com.palmelf.eoffice.model.personal;

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
@Table(name = "duty_system")
public class DutySystem extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5268659408842925885L;

	public static final Short DEFAULT = 1;

	public static final Short NOT_DEFAULT = 0;
	private Long systemId;
	private String systemName;
	private String systemSetting;
	private String systemDesc;
	private Short isDefault;

	public DutySystem() {
	}

	public DutySystem(Long in_systemId) {
		this.setSystemId(in_systemId);
	}

	@Id
	@GeneratedValue
	@Column(name = "systemId", unique = true, nullable = false)
	public Long getSystemId() {
		return this.systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	@Column(name = "systemName", nullable = false, length = 128)
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	@Column(name = "systemSetting", nullable = false, length = 128)
	public String getSystemSetting() {
		return this.systemSetting;
	}

	public void setSystemSetting(String systemSetting) {
		this.systemSetting = systemSetting;
	}

	@Column(name = "systemDesc", nullable = false, length = 256)
	public String getSystemDesc() {
		return this.systemDesc;
	}

	public void setSystemDesc(String systemDesc) {
		this.systemDesc = systemDesc;
	}

	@Column(name = "isDefault", nullable = false)
	public Short getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DutySystem)) {
			return false;
		}
		DutySystem rhs = (DutySystem) object;
		return new EqualsBuilder().append(this.systemId, rhs.systemId).append(this.systemName, rhs.systemName)
				.append(this.systemSetting, rhs.systemSetting).append(this.systemDesc, rhs.systemDesc).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.systemId).append(this.systemName)
				.append(this.systemSetting).append(this.systemDesc).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("systemId", this.systemId).append("systemName", this.systemName)
				.append("systemSetting", this.systemSetting).append("systemDesc", this.systemDesc).toString();
	}

}
