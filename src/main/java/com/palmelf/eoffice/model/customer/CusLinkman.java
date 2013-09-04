package com.palmelf.eoffice.model.customer;

import java.util.Date;

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

@Entity
@Table(name = "cus_linkman")
public class CusLinkman extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8858166200713673145L;
	private Long linkmanId;
	private String fullname;
	private Short sex;
	private String position;
	private String phone;
	private String mobile;
	private String email;
	private String msn;
	private String qq;
	private String fax;
	private Date birthday;
	private String homeAddress;
	private String homeZip;
	private String homePhone;
	private String hobby;
	private Short isPrimary;
	private String notes;
	private Customer customer;

	public CusLinkman() {
	}

	public CusLinkman(Long in_linkmanId) {
		this.setLinkmanId(in_linkmanId);
	}

	@Id
	@GeneratedValue
	@Column(name = "linkmanId", unique = true, nullable = false)
	public Long getLinkmanId() {
		return this.linkmanId;
	}

	public void setLinkmanId(Long linkmanId) {
		this.linkmanId = linkmanId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "fullname", nullable = false, length = 32)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "sex", nullable = false)
	public Short getSex() {
		return this.sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	@Column(name = "position", length = 32)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "phone", length = 32)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "mobile", nullable = false, length = 32)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "msn", length = 100)
	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	@Column(name = "qq", length = 64)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "fax", length = 32)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "birthday", length = 19)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "homeAddress", length = 128)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "homeZip", length = 32)
	public String getHomeZip() {
		return this.homeZip;
	}

	public void setHomeZip(String homeZip) {
		this.homeZip = homeZip;
	}

	@Column(name = "homePhone", length = 32)
	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "hobby", length = 100)
	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Column(name = "isPrimary", nullable = false)
	public Short getIsPrimary() {
		return this.isPrimary;
	}

	public void setIsPrimary(Short isPrimary) {
		this.isPrimary = isPrimary;
	}

	@Column(name = "notes", length = 500)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CusLinkman)) {
			return false;
		}
		CusLinkman rhs = (CusLinkman) object;
		return new EqualsBuilder().append(this.linkmanId, rhs.linkmanId).append(this.fullname, rhs.fullname)
				.append(this.sex, rhs.sex).append(this.position, rhs.position).append(this.phone, rhs.phone)
				.append(this.mobile, rhs.mobile).append(this.email, rhs.email).append(this.msn, rhs.msn)
				.append(this.qq, rhs.qq).append(this.fax, rhs.fax).append(this.birthday, rhs.birthday)
				.append(this.homeAddress, rhs.homeAddress).append(this.homeZip, rhs.homeZip)
				.append(this.homePhone, rhs.homePhone).append(this.hobby, rhs.hobby)
				.append(this.isPrimary, rhs.isPrimary).append(this.notes, rhs.notes).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.linkmanId).append(this.fullname).append(this.sex)
				.append(this.position).append(this.phone).append(this.mobile).append(this.email).append(this.msn)
				.append(this.qq).append(this.fax).append(this.birthday).append(this.homeAddress).append(this.homeZip)
				.append(this.homePhone).append(this.hobby).append(this.isPrimary).append(this.notes).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("linkmanId", this.linkmanId).append("fullname", this.fullname)
				.append("sex", this.sex).append("position", this.position).append("phone", this.phone)
				.append("mobile", this.mobile).append("email", this.email).append("msn", this.msn)
				.append("qq", this.qq).append("fax", this.fax).append("birthday", this.birthday)
				.append("homeAddress", this.homeAddress).append("homeZip", this.homeZip)
				.append("homePhone", this.homePhone).append("hobby", this.hobby).append("isPrimary", this.isPrimary)
				.append("notes", this.notes).toString();
	}
}
