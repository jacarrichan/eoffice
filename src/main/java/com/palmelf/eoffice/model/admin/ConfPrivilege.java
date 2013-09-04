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
@Table(name = "conf_privilege")
public class ConfPrivilege extends BaseModel {
	private static final long serialVersionUID = 1L;
	private Integer privilegeId;
	private Long userId;
	private Long confId;
	private String fullname;
	private Short rights;

	public ConfPrivilege() {
	}

	public ConfPrivilege(Integer in_privilegeId) {
		this.setPrivilegeId(in_privilegeId);
	}

	@Id
	@GeneratedValue
	@Column(name = "privilegeId", unique = true, nullable = false)
	public Integer getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	@Column(name = "confId")
	public Long getConfId() {
		return this.confId;
	}

	public void setConfId(Long confId) {
		this.confId = confId;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "fullname", nullable = false, length = 64)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "rights", nullable = false)
	public Short getRights() {
		return this.rights;
	}

	public void setRights(Short rights) {
		this.rights = rights;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ConfPrivilege)) {
			return false;
		}
		ConfPrivilege rhs = (ConfPrivilege) object;
		return new EqualsBuilder().append(this.privilegeId, rhs.privilegeId).append(this.confId, rhs.confId)
				.append(this.userId, rhs.userId).append(this.fullname, rhs.fullname).append(this.rights, rhs.rights)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.privilegeId).append(this.confId)
				.append(this.userId).append(this.fullname).append(this.rights).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("privilegeId", this.privilegeId).append("confId", this.confId)
				.append("userId", this.userId).append("fullname", this.fullname).append("rights", this.rights)
				.toString();
	}
}
