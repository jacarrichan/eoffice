package com.cyjt.oa.model.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jbpm.api.identity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;


@Entity
@Table(name = "app_user")
public class AppUser extends BaseModel implements UserDetails, User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1808117791510830416L;

	public static Long SYSTEM_USER = new Long(2L);

	public static Long SUPER_USER = new Long(1L);

	@Expose
	protected Long userId;

	@Expose
	protected String username;
	protected String password;

	@Expose
	protected String email;

	@Expose
	protected Department department;

	@Expose
	protected String position;

	@Expose
	protected String phone;

	@Expose
	protected String mobile;

	@Expose
	protected String fax;

	@Expose
	protected String address;

	@Expose
	protected String zip;

	@Expose
	protected String photo;

	@Expose
	protected Date accessionTime;

	@Expose
	protected Short status;

	@Expose
	protected String education;

	@Expose
	protected Short title;

	@Expose
	protected String fullname;

	@Expose
	protected Short delFlag;
	private Set<AppRole> roles;
	private Set<String> rights = new HashSet<String>();

	@Transient
	public Set<String> getRights() {
		return this.rights;
	}

	public void setRights(Set<String> rights) {
		this.rights = rights;
	}

	@Transient
	public String getFunctionRights() {
		StringBuffer sb = new StringBuffer();

		Iterator<String> it = this.rights.iterator();

		while (it.hasNext()) {
			sb.append(it.next()).append(",");
		}

		if (this.rights.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}

	public AppUser() {
	}

	public AppUser(Long in_userId) {
		this.setUserId(in_userId);
	}

	@Id
	@GeneratedValue
	@Column(name = "userId", unique = true, nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depId")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "username", nullable = false, length = 128)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 128)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = false, length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Column(name = "mobile", length = 32)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "fax", length = 32)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "address", length = 64)
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

	@Column(name = "photo", length = 128)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "accessionTime", nullable = false, length = 19)
	public Date getAccessionTime() {
		return this.accessionTime;
	}

	public void setAccessionTime(Date accessionTime) {
		this.accessionTime = accessionTime;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "delFlag", nullable = false)
	public Short getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "education", length = 64)
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "title")
	public Short getTitle() {
		return this.title;
	}

	public void setTitle(Short title) {
		this.title = title;
	}

	@Column(name = "fullname", length = 128)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "userId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "roleId", nullable = false, updatable = false) })
	public Set<AppRole> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<AppRole> roles) {
		this.roles = roles;
	}

	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		auths.addAll(this.roles);
		auths.add(new GrantedAuthorityImpl("ROLE_PUBLIC"));
		return auths;
	}

	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	public boolean isEnabled() {
		return this.status.shortValue() == 1;
	}

	@Transient
	public String getId() {
		return this.userId.toString();
	}

	@Transient
	public String getBusinessEmail() {
		return this.email;
	}

	@Transient
	public String getFamilyName() {
		return this.fullname;
	}

	@Transient
	public String getGivenName() {
		return this.fullname;
	}
}
