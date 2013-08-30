package com.cyjt.oa.model.customer;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "customer")
public class Customer extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7263819368397822434L;

	@Expose
	private Long customerId;

	@Expose
	private String customerNo;

	@Expose
	private String industryType;

	@Expose
	private String customerSource;

	@Expose
	private Short customerType;

	@Expose
	private Integer companyScale;

	@Expose
	private String customerName;

	@Expose
	private String customerManager;

	@Expose
	private String phone;

	@Expose
	private String fax;

	@Expose
	private String site;

	@Expose
	private String email;

	@Expose
	private String state;

	@Expose
	private String city;

	@Expose
	private String zip;

	@Expose
	private String address;

	@Expose
	private BigDecimal registerFun;

	@Expose
	private BigDecimal turnOver;

	@Expose
	private String currencyUnit;

	@Expose
	private String otherDesc;

	@Expose
	private String principal;

	@Expose
	private String openBank;

	@Expose
	private String accountsNo;

	@Expose
	private String taxNo;

	@Expose
	private String notes;

	@Expose
	private Short rights;
	private Set<CusLinkman> cusLinkmans = new HashSet<CusLinkman>();

	private Set<Project> projects = new HashSet<Project>();

	private Set<CusConnection> cusConnections = new HashSet<CusConnection>();

	public Customer() {
	}

	public Customer(Long in_customerId) {
		this.setCustomerId(in_customerId);
	}

	@Id
	@GeneratedValue
	@Column(name = "customerId", unique = true, nullable = false)
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "customerNo", nullable = false, length = 64)
	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	@Column(name = "industryType", nullable = false, length = 64)
	public String getIndustryType() {
		return this.industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	@Column(name = "customerSource", length = 64)
	public String getCustomerSource() {
		return this.customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	@Column(name = "customerType", nullable = false)
	public Short getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(Short customerType) {
		this.customerType = customerType;
	}

	@Column(name = "companyScale")
	public Integer getCompanyScale() {
		return this.companyScale;
	}

	public void setCompanyScale(Integer companyScale) {
		this.companyScale = companyScale;
	}

	@Column(name = "customerName", nullable = false, length = 128)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "customerManager", nullable = false, length = 32)
	public String getCustomerManager() {
		return this.customerManager;
	}

	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}

	@Column(name = "phone", nullable = false, length = 32)
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

	@Column(name = "site", length = 128)
	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Column(name = "email", length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "state", length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "city", length = 32)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "zip", length = 32)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "registerFun")
	public BigDecimal getRegisterFun() {
		return this.registerFun;
	}

	public void setRegisterFun(BigDecimal registerFun) {
		this.registerFun = registerFun;
	}

	@Column(name = "turnOver")
	public BigDecimal getTurnOver() {
		return this.turnOver;
	}

	public void setTurnOver(BigDecimal turnOver) {
		this.turnOver = turnOver;
	}

	@Column(name = "currencyUnit", length = 32)
	public String getCurrencyUnit() {
		return this.currencyUnit;
	}

	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	@Column(name = "otherDesc", length = 800)
	public String getOtherDesc() {
		return this.otherDesc;
	}

	public void setOtherDesc(String otherDesc) {
		this.otherDesc = otherDesc;
	}

	@Column(name = "principal", length = 32)
	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Column(name = "openBank", length = 64)
	public String getOpenBank() {
		return this.openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	@Column(name = "accountsNo", length = 64)
	public String getAccountsNo() {
		return this.accountsNo;
	}

	public void setAccountsNo(String accountsNo) {
		this.accountsNo = accountsNo;
	}

	@Column(name = "taxNo", length = 64)
	public String getTaxNo() {
		return this.taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	@Column(name = "notes", length = 500)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "rights", nullable = false)
	public Short getRights() {
		return this.rights;
	}

	public void setRights(Short rights) {
		this.rights = rights;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<CusLinkman> getCusLinkmans() {
		return this.cusLinkmans;
	}

	public void setCusLinkmans(Set<CusLinkman> cusLinkmans) {
		this.cusLinkmans = cusLinkmans;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<CusConnection> getCusConnections() {
		return this.cusConnections;
	}

	public void setCusConnections(Set<CusConnection> cusConnections) {
		this.cusConnections = cusConnections;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Customer)) {
			return false;
		}
		Customer rhs = (Customer) object;
		return new EqualsBuilder().append(this.customerId, rhs.customerId).append(this.customerNo, rhs.customerNo)
				.append(this.industryType, rhs.industryType).append(this.customerSource, rhs.customerSource)
				.append(this.customerType, rhs.customerType).append(this.companyScale, rhs.companyScale)
				.append(this.customerName, rhs.customerName).append(this.customerManager, rhs.customerManager)
				.append(this.phone, rhs.phone).append(this.fax, rhs.fax).append(this.site, rhs.site)
				.append(this.email, rhs.email).append(this.state, rhs.state).append(this.city, rhs.city)
				.append(this.zip, rhs.zip).append(this.address, rhs.address).append(this.registerFun, rhs.registerFun)
				.append(this.turnOver, rhs.turnOver).append(this.currencyUnit, rhs.currencyUnit)
				.append(this.otherDesc, rhs.otherDesc).append(this.principal, rhs.principal)
				.append(this.openBank, rhs.openBank).append(this.accountsNo, rhs.accountsNo)
				.append(this.taxNo, rhs.taxNo).append(this.notes, rhs.notes).append(this.rights, rhs.rights).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.customerId).append(this.customerNo)
				.append(this.industryType).append(this.customerSource).append(this.customerType)
				.append(this.companyScale).append(this.customerName).append(this.customerManager).append(this.phone)
				.append(this.fax).append(this.site).append(this.email).append(this.state).append(this.city)
				.append(this.zip).append(this.address).append(this.registerFun).append(this.turnOver)
				.append(this.currencyUnit).append(this.otherDesc).append(this.principal).append(this.openBank)
				.append(this.accountsNo).append(this.taxNo).append(this.notes).append(this.rights).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("customerId", this.customerId).append("customerNo", this.customerNo)
				.append("industryType", this.industryType).append("customerSource", this.customerSource)
				.append("customerType", this.customerType).append("companyScale", this.companyScale)
				.append("customerName", this.customerName).append("customerManager", this.customerManager)
				.append("phone", this.phone).append("fax", this.fax).append("site", this.site)
				.append("email", this.email).append("state", this.state).append("city", this.city)
				.append("zip", this.zip).append("address", this.address).append("registerFun", this.registerFun)
				.append("turnOver", this.turnOver).append("currencyUnit", this.currencyUnit)
				.append("otherDesc", this.otherDesc).append("principal", this.principal)
				.append("openBank", this.openBank).append("accountsNo", this.accountsNo).append("taxNo", this.taxNo)
				.append("notes", this.notes).append("rights", this.rights).toString();
	}
}
