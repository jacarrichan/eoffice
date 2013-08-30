package com.cyjt.oa.model.archive;

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
@Table(name = "arch_flow_conf")
public class ArchFlowConf extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3973672591073097634L;
	public static Short ARCH_SEND_TYPE = 0;
	public static Short ARCH_REC_TYPE = 1;
	private Long configId;
	private String processName;
	private Long processDefId;
	private Short archType;

	public ArchFlowConf() {
	}

	public ArchFlowConf(Long in_configId) {
		this.setConfigId(in_configId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "configId", unique = true, nullable = false)
	public Long getConfigId() {
		return this.configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	@Column(name = "processName", nullable = false, length = 128)
	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	@Column(name = "processDefId")
	public Long getProcessDefId() {
		return this.processDefId;
	}

	public void setProcessDefId(Long processDefId) {
		this.processDefId = processDefId;
	}

	@Column(name = "archType")
	public Short getArchType() {
		return this.archType;
	}

	public void setArchType(Short archType) {
		this.archType = archType;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ArchFlowConf)) {
			return false;
		}
		ArchFlowConf rhs = (ArchFlowConf) object;
		return new EqualsBuilder().append(this.configId, rhs.configId).append(this.processName, rhs.processName)
				.append(this.processDefId, rhs.processDefId).append(this.archType, rhs.archType).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.configId).append(this.processName)
				.append(this.processDefId).append(this.archType).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("configId", this.configId).append("processName", this.processName)
				.append("processDefId", this.processDefId).append("archType", this.archType).toString();
	}
}
