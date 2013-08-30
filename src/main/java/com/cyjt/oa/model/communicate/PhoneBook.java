package com.cyjt.oa.model.communicate;

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

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.AppUser;

@Entity
@Table(name = "phone_book")
public class PhoneBook extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7651819162131688256L;

	@Expose
	private Long phoneId;

	@Expose
	private String fullname;

	@Expose
	private String title;

	@Expose
	private Date birthday;

	@Expose
	private String nickName;

	@Expose
	private String duty;

	@Expose
	private String spouse;

	@Expose
	private String childs;

	@Expose
	private String companyName;

	@Expose
	private String companyAddress;

	@Expose
	private String companyPhone;

	@Expose
	private String companyFax;

	@Expose
	private String homeAddress;

	@Expose
	private String homeZip;

	@Expose
	private String mobile;

	@Expose
	private String phone;

	@Expose
	private String email;

	@Expose
	private String qqNumber;

	@Expose
	private String msn;

	@Expose
	private String note;

	@Expose
	private Short isShared;
	private AppUser appUser;

	@Expose
	private PhoneGroup phoneGroup;

	public PhoneBook() {
	}

	public PhoneBook(Long in_phoneId) {
		this.setPhoneId(in_phoneId);
	}

	@Id
	@GeneratedValue
	@Column(name = "phoneId", unique = true, nullable = false)
	public Long getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupId")
	public PhoneGroup getPhoneGroup() {
		return this.phoneGroup;
	}

	public void setPhoneGroup(PhoneGroup phoneGroup) {
		this.phoneGroup = phoneGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "fullname", nullable = false, length = 128)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "title", nullable = false, length = 32)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "birthday", length = 19)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "nickName", length = 32)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "duty", length = 50)
	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(name = "spouse", length = 32)
	public String getSpouse() {
		return this.spouse;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	@Column(name = "childs", length = 40)
	public String getChilds() {
		return this.childs;
	}

	public void setChilds(String childs) {
		this.childs = childs;
	}

	@Column(name = "companyName", length = 100)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "companyAddress", length = 128)
	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Column(name = "companyPhone", length = 32)
	public String getCompanyPhone() {
		return this.companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	@Column(name = "companyFax", length = 32)
	public String getCompanyFax() {
		return this.companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	@Column(name = "homeAddress", length = 128)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "homeZip", length = 12)
	public String getHomeZip() {
		return this.homeZip;
	}

	public void setHomeZip(String homeZip) {
		this.homeZip = homeZip;
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

	@Column(name = "email", length = 32)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "QQ", length = 64)
	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	@Column(name = "MSN", length = 128)
	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	@Column(name = "note", length = 500)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "isShared", nullable = false)
	public Short getIsShared() {
		return this.isShared;
	}

	public void setIsShared(Short isShared) {
		this.isShared = isShared;
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
	@Transient
	public Long getGroupId() {
		return this.getPhoneGroup() == null ? null : this.getPhoneGroup().getGroupId();
	}

	public void setGroupId(Long aValue) {
		if (aValue == null) {
			this.phoneGroup = null;
		} else if (this.phoneGroup == null) {
			this.phoneGroup = new PhoneGroup(aValue);
			this.phoneGroup.setVersion(new Integer(0));
		} else {
			this.phoneGroup.setGroupId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PhoneBook)) {
			return false;
		}
		PhoneBook rhs = (PhoneBook) object;
		return new EqualsBuilder().append(this.phoneId, rhs.phoneId).append(this.fullname, rhs.fullname)
				.append(this.title, rhs.title).append(this.birthday, rhs.birthday).append(this.nickName, rhs.nickName)
				.append(this.duty, rhs.duty).append(this.spouse, rhs.spouse).append(this.childs, rhs.childs)
				.append(this.companyName, rhs.companyName).append(this.companyAddress, rhs.companyAddress)
				.append(this.companyPhone, rhs.companyPhone).append(this.companyFax, rhs.companyFax)
				.append(this.homeAddress, rhs.homeAddress).append(this.homeZip, rhs.homeZip)
				.append(this.mobile, rhs.mobile).append(this.phone, rhs.phone).append(this.email, rhs.email)
				.append(this.qqNumber, rhs.qqNumber).append(this.msn, rhs.msn).append(this.note, rhs.note)
				.append(this.isShared, rhs.isShared).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.phoneId).append(this.fullname).append(this.title)
				.append(this.birthday).append(this.nickName).append(this.duty).append(this.spouse).append(this.childs)
				.append(this.companyName).append(this.companyAddress).append(this.companyPhone).append(this.companyFax)
				.append(this.homeAddress).append(this.homeZip).append(this.mobile).append(this.phone)
				.append(this.email).append(this.qqNumber).append(this.msn).append(this.note).append(this.isShared)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("phoneId", this.phoneId).append("fullname", this.fullname)
				.append("title", this.title).append("birthday", this.birthday).append("nickName", this.nickName)
				.append("duty", this.duty).append("spouse", this.spouse).append("childs", this.childs)
				.append("companyName", this.companyName).append("companyAddress", this.companyAddress)
				.append("companyPhone", this.companyPhone).append("companyFax", this.companyFax)
				.append("homeAddress", this.homeAddress).append("homeZip", this.homeZip).append("mobile", this.mobile)
				.append("phone", this.phone).append("email", this.email).append("qqNumber", this.qqNumber)
				.append("msn", this.msn).append("note", this.note).append("isShared", this.isShared).toString();
	}


	
}
