package com.palmelf.eoffice.model.hrm;

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

import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.Department;

@Entity
@Table(name = "job")
public class Job extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2809740680937752365L;
	public static short DELFLAG_NOT = 0;
	public static short DELFLAG_HAD = 1;
	private Long jobId;
	private String jobName;
	private String memo;
	private Short delFlag;
	private Department department;
	private Set<EmpProfile> empProfiles = new HashSet<EmpProfile>();

	public Job() {
	}

	public Job(Long in_jobId) {
		this.setJobId(in_jobId);
	}

	@Transient
	public Long getDepId() {
		return this.getDepartment() == null ? null : this.getDepartment()
				.getDepId();
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "jobId", unique = true, nullable = false)
	public Long getJobId() {
		return this.jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depId")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "jobName", nullable = false, length = 128)
	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Column(name = "memo", length = 512)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "delFlag")
	public Short getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "jobId")
	public Set<EmpProfile> getEmpProfiles() {
		return this.empProfiles;
	}

	public void setEmpProfiles(Set<EmpProfile> empProfiles) {
		this.empProfiles = empProfiles;
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
		if (!(object instanceof Job)) {
			return false;
		}
		Job rhs = (Job) object;
		return new EqualsBuilder().append(this.jobId, rhs.jobId)
				.append(this.jobName, rhs.jobName).append(this.memo, rhs.memo)
				.append(this.delFlag, rhs.delFlag).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.jobId)
				.append(this.jobName).append(this.memo).append(this.delFlag)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("jobId", this.jobId)
				.append("jobName", this.jobName).append("memo", this.memo)
				.append("delFlag", this.delFlag).toString();
	}
}
