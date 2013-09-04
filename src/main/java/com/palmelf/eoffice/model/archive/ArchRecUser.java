package com.palmelf.eoffice.model.archive;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.Department;

@Entity
@Table(name = "arch_rec_user")
public class ArchRecUser extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3965525928742612893L;
	private Long archRecId;
	private Long userId;
	private String fullname;
	private Long depId;

	private String depName;
	private Department department;

	public ArchRecUser() {
	}

	public ArchRecUser(Long in_archRecId) {
		this.setArchRecId(in_archRecId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "archRecId", unique = true, nullable = false)
	public Long getArchRecId() {
		return this.archRecId;
	}

	public void setArchRecId(Long archRecId) {
		this.archRecId = archRecId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depId")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "fullname", nullable = false, length = 128)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "depName", nullable = false, length = 128)
	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}
	@Transient
	public Long getDepId() {
		return this.depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ArchRecUser)) {
			return false;
		}
		ArchRecUser rhs = (ArchRecUser) object;
		return new EqualsBuilder().append(this.archRecId, rhs.archRecId).append(this.userId, rhs.userId)
				.append(this.fullname, rhs.fullname).append(this.depName, rhs.depName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.archRecId).append(this.userId)
				.append(this.fullname).append(this.depName).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("archRecId", this.archRecId).append("userId", this.userId)
				.append("fullname", this.fullname).append("depName", this.depName).toString();
	}
}
