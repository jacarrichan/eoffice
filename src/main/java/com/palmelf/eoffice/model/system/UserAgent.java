package com.palmelf.eoffice.model.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "user_agent")
public class UserAgent extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1557975044608091768L;
	private Long grantId;
	private Long userId;
	private String fullname;
	private Long grantUId;
	private String grantFullname;
	private Integer grantTitle;

	public UserAgent() {
	}

	public UserAgent(Long in_grantId) {
		this.setGrantId(in_grantId);
	}

	@Id
	@GeneratedValue
	@Column(name = "grantId", unique = true, nullable = false)
	public Long getGrantId() {
		return this.grantId;
	}

	public void setGrantId(Long grantId) {
		this.grantId = grantId;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "fullname", length = 64)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "grantFullname", length = 64)
	public String getGrantFullname() {
		return this.grantFullname;
	}

	public void setGrantFullname(String grantFullname) {
		this.grantFullname = grantFullname;
	}

	@Column(name = "grantTitle")
	public Integer getGrantTitle() {
		return this.grantTitle;
	}

	public void setGrantTitle(Integer grantTitle) {
		this.grantTitle = grantTitle;
	}

	@Column(name = "grantUId", length = 19)
	public Long getGrantUId() {
		return this.grantUId;
	}

	public void setGrantUId(Long grantUId) {
		this.grantUId = grantUId;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserAgent)) {
			return false;
		}
		UserAgent rhs = (UserAgent) object;
		return new EqualsBuilder().append(this.grantId, rhs.grantId).append(this.userId, rhs.userId)
				.append(this.fullname, rhs.fullname).append(this.grantUId, rhs.grantUId)
				.append(this.grantFullname, rhs.grantFullname).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.grantId).append(this.userId)
				.append(this.fullname).append(this.grantUId).append(this.grantFullname).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("grantId", this.grantId).append("userId", this.userId)
				.append("fullname", this.fullname).append("grantUId", this.grantUId)
				.append("grantFullname", this.grantFullname).toString();
	}

}
