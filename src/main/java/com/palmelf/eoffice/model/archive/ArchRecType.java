package com.palmelf.eoffice.model.archive;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.Department;

@Entity
@Table(name = "arch_rec_type")
public class ArchRecType extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8258009319786167936L;

	@Expose
	private Long recTypeId;

	@Expose
	private String typeName;

	@Expose
	private Department department;

	private Set<ArchivesDep> archivesDeps = new HashSet<ArchivesDep>();

	private Set<Archives> archives = new HashSet<Archives>();

	public ArchRecType() {
	}

	public ArchRecType(Long in_typeId) {
		this.setRecTypeId(in_typeId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "recTypeId", unique = true, nullable = false)
	public Long getRecTypeId() {
		return this.recTypeId;
	}

	public void setRecTypeId(Long typeId) {
		this.recTypeId = typeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depId")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "typeName", nullable = false, length = 128)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId")
	public Set<ArchivesDep> getArchivesDeps() {
		return this.archivesDeps;
	}

	public void setArchivesDeps(Set<ArchivesDep> archivesDeps) {
		this.archivesDeps = archivesDeps;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "arc_typeId")
	public Set<Archives> getArchives() {
		return archives;
	}

	public void setArchives(Set<Archives> archives) {
		this.archives = archives;
	}
	@Transient
	public Long getDepId() {
		return this.getDepartment() == null ? null : this.getDepartment().getDepId();
	}

	public void setDepId(Long aValue) {
		if (aValue == null) {
			this.department = null;
		} else if (this.department == null) {
			this.department = new Department(aValue);
			this.department.setVersion(new Integer(0));
		} else {
			this.department.setDepId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ArchRecType)) {
			return false;
		}
		ArchRecType rhs = (ArchRecType) object;
		return new EqualsBuilder().append(this.recTypeId, rhs.recTypeId).append(this.typeName, rhs.typeName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.recTypeId).append(this.typeName).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("typeId", this.recTypeId).append("typeName", this.typeName).toString();
	}
}
