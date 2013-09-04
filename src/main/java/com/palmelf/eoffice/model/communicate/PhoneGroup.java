package com.palmelf.eoffice.model.communicate;

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
import com.palmelf.eoffice.model.system.AppUser;
@Entity
@Table(name = "phone_group")
public class PhoneGroup extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2881622922321002924L;

	@Expose
	private Long groupId;

	@Expose
	private String groupName;

	@Expose
	private Short isShared;

	@Expose
	private Integer sn;
	private AppUser appUser;
	private Set<PhoneBook> phoneBooks = new HashSet<PhoneBook>();

	public PhoneGroup() {
	}

	public PhoneGroup(Long in_groupId) {
		this.setGroupId(in_groupId);
	}

	@Id
	@GeneratedValue
	@Column(name = "groupId", unique = true, nullable = false)
	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "groupName", nullable = false, length = 128)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "isShared", nullable = false)
	public Short getIsShared() {
		return this.isShared;
	}

	public void setIsShared(Short isShared) {
		this.isShared = isShared;
	}

	@Column(name = "SN", nullable = false)
	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "phoneGroup")
	public Set<PhoneBook> getPhoneBooks() {
		return this.phoneBooks;
	}

	public void setPhoneBooks(Set<PhoneBook> phoneBooks) {
		this.phoneBooks = phoneBooks;
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
		if (!(object instanceof PhoneGroup)) {
			return false;
		}
		PhoneGroup rhs = (PhoneGroup) object;
		return new EqualsBuilder().append(this.groupId, rhs.groupId).append(this.groupName, rhs.groupName)
				.append(this.isShared, rhs.isShared).append(this.sn, rhs.sn).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.groupId).append(this.groupName)
				.append(this.isShared).append(this.sn).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("groupId", this.groupId).append("groupName", this.groupName)
				.append("isShared", this.isShared).append("sn", this.sn).toString();
	}

}
