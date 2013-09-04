package com.palmelf.eoffice.model.archive;

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
@Table(name = "arch_dispatch")
public class ArchDispatch extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5846711785856647755L;
	public static Short HAVE_READ = 1;
	public static Short NOT_READ = 0;
	public static Short IS_UNDERTAKE = 1;
	public static Short IS_READER = 0;
	public static Short IS_DISPATCH = 2;

	public static Short TYPE_DISPATCH = 2;

	public static Short TYPE_HANDLE = 0;

	public static Short TYPE_APPLY = 1;

	public static Short TYPE_BACK = 3;
	private Long dispatchId;
	private Date dispatchTime;
	private Long userId;
	private String fullname;
	private Short isRead;
	private String subject;
	private String readFeedback;
	private Short archUserType;
	private Long disRoleId;
	private String disRoleName;
	private Archives archives;

	public ArchDispatch() {
	}

	public ArchDispatch(Long in_dispatchId) {
		this.setDispatchId(in_dispatchId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "dispatchId", unique = true, nullable = false)
	public Long getDispatchId() {
		return this.dispatchId;
	}

	public void setDispatchId(Long dispatchId) {
		this.dispatchId = dispatchId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "archivesId")
	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	@Column(name = "dispatchTime", nullable = false, length = 19)
	public Date getDispatchTime() {
		return this.dispatchTime;
	}

	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "fullname", length = 128)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "isRead")
	public Short getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Short isRead) {
		this.isRead = isRead;
	}

	@Column(name = "subject", length = 256)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "readFeedback", length = 1024)
	public String getReadFeedback() {
		return this.readFeedback;
	}

	public void setReadFeedback(String readFeedback) {
		this.readFeedback = readFeedback;
	}

	@Column(name = "archUserType")
	public Short getArchUserType() {
		return this.archUserType;
	}

	public void setArchUserType(Short archUserType) {
		this.archUserType = archUserType;
	}

	@Column(name = "disRoleId")
	public Long getDisRoleId() {
		return this.disRoleId;
	}

	public void setDisRoleId(Long disRoleId) {
		this.disRoleId = disRoleId;
	}

	@Column(name = "disRoleName", length = 128)
	public String getDisRoleName() {
		return this.disRoleName;
	}

	public void setDisRoleName(String disRoleName) {
		this.disRoleName = disRoleName;
	}
	@Transient
	public Long getArchivesId() {
		return this.getArchives() == null ? null : this.getArchives().getArchivesId();
	}

	public void setArchivesId(Long aValue) {
		if (aValue == null) {
			this.archives = null;
		} else if (this.archives == null) {
			this.archives = new Archives(aValue);
			this.archives.setVersion(new Integer(0));
		} else {
			this.archives.setArchivesId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ArchDispatch)) {
			return false;
		}
		ArchDispatch rhs = (ArchDispatch) object;
		return new EqualsBuilder().append(this.dispatchId, rhs.dispatchId).append(this.dispatchTime, rhs.dispatchTime)
				.append(this.userId, rhs.userId).append(this.fullname, rhs.fullname).append(this.isRead, rhs.isRead)
				.append(this.subject, rhs.subject).append(this.readFeedback, rhs.readFeedback)
				.append(this.archUserType, rhs.archUserType).append(this.disRoleId, rhs.disRoleId)
				.append(this.disRoleName, rhs.disRoleName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.dispatchId).append(this.dispatchTime)
				.append(this.userId).append(this.fullname).append(this.isRead).append(this.subject)
				.append(this.readFeedback).append(this.archUserType).append(this.disRoleId).append(this.disRoleName)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("dispatchId", this.dispatchId)
				.append("dispatchTime", this.dispatchTime).append("userId", this.userId)
				.append("fullname", this.fullname).append("isRead", this.isRead).append("subject", this.subject)
				.append("readFeedback", this.readFeedback).append("isUndertake", this.archUserType)
				.append("disRoleId", this.disRoleId).append("disRoleName", this.disRoleName).toString();
	}
}
