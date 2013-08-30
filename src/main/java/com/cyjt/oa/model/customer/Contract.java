package com.cyjt.oa.model.customer;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.FileAttach;

import java.math.BigDecimal;
import java.util.Date;
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

@Entity
@Table(name = "contract")
public class Contract extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Expose
	private Long contractId;

	@Expose
	private String contractNo;

	@Expose
	private String subject;

	@Expose
	private BigDecimal contractAmount;

	@Expose
	private String mainItem;

	@Expose
	private String salesAfterItem;

	@Expose
	private Date validDate;

	@Expose
	private Date expireDate;

	@Expose
	private String serviceDep;

	@Expose
	private String serviceMan;

	@Expose
	private String signupUser;

	@Expose
	private Date signupTime;

	@Expose
	private String creator;

	@Expose
	private Date createtime;

	@Expose
	private String consignAddress;

	@Expose
	private String consignee;

	@Expose
	private Project project;
	private Set<ContractConfig> contractConfigs = new HashSet<ContractConfig>();

	@Expose
	private Set<FileAttach> contractFiles = new HashSet<FileAttach>();

	public Contract() {
	}

	public Contract(Long in_contractId) {
		setContractId(in_contractId);
	}

	@Id
	@GeneratedValue
	@Column(name = "contractId", unique = true, nullable = false)
	public Long getContractId() {
		return this.contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "contractNo", nullable = false, length = 64)
	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "subject", nullable = false, length = 128)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "contractAmount", nullable = false)
	public BigDecimal getContractAmount() {
		return this.contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	@Column(name = "mainItem", length = 4000)
	public String getMainItem() {
		return this.mainItem;
	}

	public void setMainItem(String mainItem) {
		this.mainItem = mainItem;
	}

	@Column(name = "salesAfterItem", length = 4000)
	public String getSalesAfterItem() {
		return this.salesAfterItem;
	}

	public void setSalesAfterItem(String salesAfterItem) {
		this.salesAfterItem = salesAfterItem;
	}

	@Column(name = "validDate", nullable = false, length = 19)
	public Date getValidDate() {
		return this.validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	@Column(name = "expireDate", nullable = false, length = 19)
	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Column(name = "serviceDep", length = 64)
	public String getServiceDep() {
		return this.serviceDep;
	}

	public void setServiceDep(String serviceDep) {
		this.serviceDep = serviceDep;
	}

	@Column(name = "serviceMan", length = 64)
	public String getServiceMan() {
		return this.serviceMan;
	}

	public void setServiceMan(String serviceMan) {
		this.serviceMan = serviceMan;
	}

	@Column(name = "signupUser", nullable = false, length = 64)
	public String getSignupUser() {
		return this.signupUser;
	}

	public void setSignupUser(String signupUser) {
		this.signupUser = signupUser;
	}

	@Column(name = "signupTime", nullable = false, length = 19)
	public Date getSignupTime() {
		return this.signupTime;
	}

	public void setSignupTime(Date signupTime) {
		this.signupTime = signupTime;
	}

	@Column(name = "creator", nullable = false, length = 32)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "consignAddress", length = 128)
	public String getConsignAddress() {
		return this.consignAddress;
	}

	public void setConsignAddress(String consignAddress) {
		this.consignAddress = consignAddress;
	}

	@Column(name = "consignee", length = 32)
	public String getConsignee() {
		return this.consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contract")
	public Set<ContractConfig> getContractConfigs() {
		return this.contractConfigs;
	}

	public void setContractConfigs(Set<ContractConfig> contractConfigs) {
		this.contractConfigs = contractConfigs;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "contract_file", joinColumns = { @JoinColumn(name = "contractId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "fileId", nullable = false, updatable = false) })
	public Set<FileAttach> getContractFiles() {
		return this.contractFiles;
	}

	public void setContractFiles(Set<FileAttach> fileAttachs) {
		this.contractFiles = fileAttachs;
	}
	@Transient
	public Long getProjectId() {
		return getProject() == null ? null : getProject().getProjectId();
	}

	public void setProjectId(Long aValue) {
		if (aValue == null) {
			this.project = null;
		} else if (this.project == null) {
			this.project = new Project(aValue);
			this.project.setVersion(new Integer(0));
		} else {
			this.project.setProjectId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Contract)) {
			return false;
		}
		Contract rhs = (Contract) object;
		return new EqualsBuilder().append(this.contractId, rhs.contractId).append(this.contractNo, rhs.contractNo)
				.append(this.subject, rhs.subject).append(this.contractAmount, rhs.contractAmount)
				.append(this.mainItem, rhs.mainItem).append(this.salesAfterItem, rhs.salesAfterItem)
				.append(this.validDate, rhs.validDate).append(this.expireDate, rhs.expireDate)
				.append(this.serviceDep, rhs.serviceDep).append(this.serviceMan, rhs.serviceMan)
				.append(this.signupUser, rhs.signupUser).append(this.signupTime, rhs.signupTime)
				.append(this.creator, rhs.creator).append(this.createtime, rhs.createtime)
				.append(this.consignAddress, rhs.consignAddress).append(this.consignee, rhs.consignee).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.contractId).append(this.contractNo)
				.append(this.subject).append(this.contractAmount).append(this.mainItem).append(this.salesAfterItem)
				.append(this.validDate).append(this.expireDate).append(this.serviceDep).append(this.serviceMan)
				.append(this.signupUser).append(this.signupTime).append(this.creator).append(this.createtime)
				.append(this.consignAddress).append(this.consignee).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("contactId", this.contractId).append("contractNo", this.contractNo)
				.append("subject", this.subject).append("contractAmount", this.contractAmount)
				.append("mainItem", this.mainItem).append("salesAfterItem", this.salesAfterItem)
				.append("validDate", this.validDate).append("expireDate", this.expireDate)
				.append("serviceDep", this.serviceDep).append("serviceMan", this.serviceMan)
				.append("signupUser", this.signupUser).append("signupTime", this.signupTime)
				.append("creator", this.creator).append("createtime", this.createtime)
				.append("consignAddress", this.consignAddress).append("consignee", this.consignee).toString();
	}
}
