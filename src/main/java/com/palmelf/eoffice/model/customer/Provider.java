package com.palmelf.eoffice.model.customer;

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
import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "provider")
public class Provider extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5684092137560404478L;

	@Expose
	private Long providerId;

	@Expose
	private String providerName;

	@Expose
	private String contactor;

	@Expose
	private String phone;

	@Expose
	private String fax;

	@Expose
	private String site;

	@Expose
	private String email;

	@Expose
	private String address;

	@Expose
	private String zip;

	@Expose
	private String openBank;

	@Expose
	private String account;

	@Expose
	private String notes;

	@Expose
	private Integer rank;
	private Set<Product> products = new HashSet<Product>();

	public Provider() {
	}

	public Provider(Long in_providerId) {
		this.setProviderId(in_providerId);
	}

	@Id
	@GeneratedValue
	@Column(name = "providerId", unique = true, nullable = false)
	public Long getProviderId() {
		return this.providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	@Column(name = "providerName", nullable = false, length = 128)
	public String getProviderName() {
		return this.providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	@Column(name = "contactor", nullable = false, length = 128)
	public String getContactor() {
		return this.contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
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

	@Column(name = "address", nullable = false, length = 128)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zip", length = 32)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "openBank", length = 128)
	public String getOpenBank() {
		return this.openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	@Column(name = "account", length = 64)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "notes", length = 500)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "rank")
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "provider")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Provider)) {
			return false;
		}
		Provider rhs = (Provider) object;
		return new EqualsBuilder().append(this.providerId, rhs.providerId).append(this.providerName, rhs.providerName)
				.append(this.contactor, rhs.contactor).append(this.phone, rhs.phone).append(this.fax, rhs.fax)
				.append(this.site, rhs.site).append(this.email, rhs.email).append(this.address, rhs.address)
				.append(this.zip, rhs.zip).append(this.openBank, rhs.openBank).append(this.account, rhs.account)
				.append(this.notes, rhs.notes).append(this.rank, rhs.rank).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.providerId).append(this.providerName)
				.append(this.contactor).append(this.phone).append(this.fax).append(this.site).append(this.email)
				.append(this.address).append(this.zip).append(this.openBank).append(this.account).append(this.notes)
				.append(this.rank).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("providerId", this.providerId)
				.append("providerName", this.providerName).append("contactor", this.contactor)
				.append("phone", this.phone).append("fax", this.fax).append("site", this.site)
				.append("email", this.email).append("address", this.address).append("zip", this.zip)
				.append("openBank", this.openBank).append("account", this.account).append("notes", this.notes)
				.append("rank", this.rank).toString();
	}
}
