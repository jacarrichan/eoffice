package com.palmelf.eoffice.model.system;

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
import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "user_sub")
public class UserSub extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3634775752990449141L;

	@Expose
	private Long subId;

	@Expose
	private AppUser subAppUser;

	@Expose
	private Long userId;

	public UserSub() {
	}

	public UserSub(Long in_subId) {
		this.setSubId(in_subId);
	}

	@Id
	@GeneratedValue
	@Column(name = "subId", unique = true, nullable = false)
	public Long getSubId() {
		return this.subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subUserId")
	public AppUser getSubAppUser() {
		return this.subAppUser;
	}

	public void setSubAppUser(AppUser subAppUser) {
		this.subAppUser = subAppUser;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Transient
	public Long getSubUserId() {
		return this.getSubAppUser() == null ? null : this.subAppUser.getUserId();
	}

	public void setSubUserId(Long aValue) {
		if (aValue == null) {
			this.subAppUser = null;
		} else if (this.subAppUser == null) {
			this.subAppUser = new AppUser(aValue);
			this.subAppUser.setVersion(new Integer(0));
		} else {
			this.subAppUser.setUserId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserSub)) {
			return false;
		}
		UserSub rhs = (UserSub) object;
		return new EqualsBuilder().append(this.subId, rhs.subId).append(this.userId, rhs.userId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.subId).append(this.userId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("subId", this.subId).append("userId", this.userId).toString();
	}
}
