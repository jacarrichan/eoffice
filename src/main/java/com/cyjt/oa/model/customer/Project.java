package com.cyjt.oa.model.customer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.FileAttach;

@Entity
@Table(name = "project")
public class Project extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7401059456619209408L;

	@Expose
	private Long projectId;

	@Expose
	private String projectName;

	@Expose
	private String projectNo;

	@Expose
	private String reqDesc;

	@Expose
	private Short isContract;

	@Expose
	private String fullname;

	@Expose
	private String mobile;

	@Expose
	private String phone;

	@Expose
	private String fax;

	@Expose
	private String otherContacts;

	@Expose
	private AppUser appUser;

	@Expose
	private Customer customer;
	private Set<Contract> contracts = new HashSet<Contract>();

	@Expose
	private Set<FileAttach> projectFiles = new HashSet<FileAttach>();

	public Project() {
	}

	public Project(Long in_projectId) {
		this.setProjectId(in_projectId);
	}

	@Id
	@GeneratedValue
	@Column(name = "projectId", unique = true, nullable = false)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "projectName", nullable = false, length = 128)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "projectNo", nullable = false, length = 64)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "reqDesc")
	public String getReqDesc() {
		return this.reqDesc;
	}

	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}

	@Column(name = "isContract", nullable = false)
	public Short getIsContract() {
		return this.isContract;
	}

	public void setIsContract(Short isContract) {
		this.isContract = isContract;
	}

	@Column(name = "fullname", nullable = false, length = 32)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "mobile", length = 32)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "phone", length = 32)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "fax", length = 32)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "otherContacts", length = 128)
	public String getOtherContacts() {
		return this.otherContacts;
	}

	public void setOtherContacts(String otherContacts) {
		this.otherContacts = otherContacts;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "project_file", joinColumns = { @JoinColumn(name = "projectId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "fileId", nullable = false, updatable = false) })
	public Set<FileAttach> getProjectFiles() {
		return this.projectFiles;
	}

	public void setProjectFiles(Set<FileAttach> projectFiles) {
		this.projectFiles = projectFiles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Contract> getContracts() {
		return this.contracts;
	}

	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}
	@Transient
	public Long getCustomerId() {
		return this.getCustomer() == null ? null : this.getCustomer().getCustomerId();
	}

	public void setCustomerId(Long aValue) {
		if (aValue == null) {
			this.customer = null;
		} else if (this.customer == null) {
			this.customer = new Customer(aValue);
			this.customer.setVersion(new Integer(0));
		} else {
			this.customer.setCustomerId(aValue);
		}
	}
	@Transient
	public Long getUserId() {
		return this.getAppUser() == null ? null : this.getAppUser().getUserId();
	}

	public void setUserId(Long aValue) {
		if (aValue == null) {
			this.appUser = null;
		} else if (this.appUser == null) {
			this.appUser = new AppUser(aValue);
			this.appUser.setVersion(new Integer(0));
		} else {
			this.appUser.setUserId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Project)) {
			return false;
		}
		Project rhs = (Project) object;
		return new EqualsBuilder().append(this.projectId, rhs.projectId).append(this.projectName, rhs.projectName)
				.append(this.projectNo, rhs.projectNo).append(this.reqDesc, rhs.reqDesc)
				.append(this.isContract, rhs.isContract).append(this.fullname, rhs.fullname)
				.append(this.mobile, rhs.mobile).append(this.phone, rhs.phone).append(this.fax, rhs.fax)
				.append(this.otherContacts, rhs.otherContacts).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.projectId).append(this.projectName)
				.append(this.projectNo).append(this.reqDesc).append(this.isContract).append(this.fullname)
				.append(this.mobile).append(this.phone).append(this.fax).append(this.otherContacts).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("projectId", this.projectId).append("projectName", this.projectName)
				.append("projectNo", this.projectNo).append("reqDesc", this.reqDesc)
				.append("isContract", this.isContract).append("fullname", this.fullname).append("mobile", this.mobile)
				.append("phone", this.phone).append("fax", this.fax).append("otherContacts", this.otherContacts)
				.toString();
	}
}
