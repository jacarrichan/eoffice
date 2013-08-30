package com.cyjt.oa.model.system;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "company")
public class Company extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3700958033213397410L;
	private Long companyId;
	private String companyNo;
	private String companyName;
	private String companyDesc;
	private String legalPerson;
	private Date setup;
	private String phone;
	private String fax;
	private String site;
	private String logo;

	@Id
	@GeneratedValue
	@Column(name = "companyId", unique = true, nullable = false)
	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Column(name = "companyNo")
	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	@Column(name = "companyName", nullable = false)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "companyDesc")
	public String getCompanyDesc() {
		return this.companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}

	@Column(name = "legalPerson")
	public String getLegalPerson() {
		return this.legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	@Column(name = "setup", length = 19)
	public Date getSetup() {
		return this.setup;
	}

	public void setSetup(Date setup) {
		this.setup = setup;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "fax")
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "site")
	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Column(name = "logo")
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
