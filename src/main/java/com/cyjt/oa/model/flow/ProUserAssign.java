package com.cyjt.oa.model.flow;

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
@Table(name = "pro_user_assign")
public class ProUserAssign extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7370711384028485551L;
	protected Long assignId;
	protected String deployId;
	protected String activityName;
	protected String roleId;
	protected String roleName;
	protected String userId;
	protected String username;
	protected Short isSigned;

	public ProUserAssign() {
	}

	public ProUserAssign(Long in_assignId) {
		this.setAssignId(in_assignId);
	}

	@Id
	@GeneratedValue
	@Column(name = "assignId", unique = true, nullable = false)
	public Long getAssignId() {
		return this.assignId;
	}

	public void setAssignId(Long assignId) {
		this.assignId = assignId;
	}

	@Column(name = "deployId", nullable = false, length = 128)
	public String getDeployId() {
		return this.deployId;
	}

	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}

	@Column(name = "activityName", nullable = false, length = 128)
	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@Column(name = "roleId", length = 128)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "roleName", length = 256)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "userId", length = 128)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "username", length = 256)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "isSigned")
	public Short getIsSigned() {
		return this.isSigned;
	}

	public void setIsSigned(Short isSigned) {
		this.isSigned = isSigned;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ProUserAssign)) {
			return false;
		}
		ProUserAssign rhs = (ProUserAssign) object;
		return new EqualsBuilder().append(this.assignId, rhs.assignId).append(this.deployId, rhs.deployId)
				.append(this.activityName, rhs.activityName).append(this.roleId, rhs.roleId)
				.append(this.userId, rhs.userId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.assignId).append(this.deployId)
				.append(this.activityName).append(this.roleId).append(this.userId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("assignId", this.assignId).append("deployId", this.deployId)
				.append("activityName", this.activityName).append("roleId", this.roleId).append("userId", this.userId)
				.toString();
	}

}
