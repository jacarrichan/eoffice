package com.palmelf.eoffice.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "conf_attend")
public class ConfAttend extends BaseModel {
	private static final long serialVersionUID = 1L;
	private Conference confId;
	private Integer attendId;
	private Integer userId;
	private Short userType;
	private String fullname;

	public ConfAttend() {
	}

	public ConfAttend(Integer in_attendId) {
		this.setAttendId(in_attendId);
	}

	@Id
	@GeneratedValue
	@Column(name = "attendId", unique = true, nullable = false)
	public Integer getAttendId() {
		return this.attendId;
	}

	public void setAttendId(Integer attendId) {
		this.attendId = attendId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "confId")
	public Conference getConference() {
		return this.confId;
	}

	public void setConference(Conference confId) {
		this.confId = confId;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "userType")
	public Short getUserType() {
		return this.userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	@Column(name = "fullname", length = 50)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ConfAttend)) {
			return false;
		}
		ConfAttend rhs = (ConfAttend) object;
		return new EqualsBuilder().append(this.attendId, rhs.attendId).append(this.confId, rhs.confId)
				.append(this.userId, rhs.userId).append(this.userType, rhs.userType)
				.append(this.fullname, rhs.fullname).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.attendId).append(this.confId).append(this.userId)
				.append(this.userType).append(this.fullname).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("attendId", this.attendId).append("confId", this.confId)
				.append("userId", this.userId).append("userType", this.userType).append("fullname", this.fullname)
				.toString();
	}
}
