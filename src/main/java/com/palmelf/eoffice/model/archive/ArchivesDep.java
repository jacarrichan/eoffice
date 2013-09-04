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
import com.palmelf.eoffice.model.system.Department;

@Entity
@Table(name = "archives_dep")
public class ArchivesDep extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4692555200359571621L;

	public static final Short RECEIVE_MAIN = 1;

	public static final Short RECEIVE_COPY = 0;

	public static final Short STATUS_SIGNED = 1;

	public static final Short STATUS_UNSIGNED = 0;
	private Long archDepId;
	private String signNo;
	private String subject;
	private Short status;
	private Date signTime;
	private String signFullname;
	private Long signUserID;
	private String handleFeedback;
	private Short isMain;
	private Archives archives;
	private Department department;

	public ArchivesDep() {
	}

	public ArchivesDep(Long in_archDepId) {
		this.setArchDepId(in_archDepId);
	}
	@Transient
	public Long getDepId() {
		return this.getDepartment() == null ? null : this.getDepartment().getDepId();
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "archDepId", unique = true, nullable = false)
	public Long getArchDepId() {
		return this.archDepId;
	}

	public void setArchDepId(Long archDepId) {
		this.archDepId = archDepId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "archivesId")
	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depId")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "signNo", length = 128)
	public String getSignNo() {
		return this.signNo;
	}

	public void setSignNo(String signNo) {
		this.signNo = signNo;
	}

	@Column(name = "subject", nullable = false, length = 256)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "signTime", length = 19)
	public Date getSignTime() {
		return this.signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	@Column(name = "signFullname", length = 64)
	public String getSignFullname() {
		return this.signFullname;
	}

	public void setSignFullname(String signFullname) {
		this.signFullname = signFullname;
	}

	@Column(name = "signUserID")
	public Long getSignUserID() {
		return signUserID;
	}

	public void setSignUserID(Long signUserID) {
		this.signUserID = signUserID;
	}

	@Column(name = "handleFeedback", length = 1024)
	public String getHandleFeedback() {
		return this.handleFeedback;
	}

	public void setHandleFeedback(String handleFeedback) {
		this.handleFeedback = handleFeedback;
	}

	@Column(name = "isMain", nullable = false)
	public Short getIsMain() {
		return this.isMain;
	}

	public void setIsMain(Short isMain) {
		this.isMain = isMain;
	}

	public void setDepId(Long aValue) {
		if (aValue == null) {
			this.department = null;
		} else if (this.department == null) {
			this.department = new Department(aValue);
			this.department.setVersion(new Integer(0));
		} else {
			this.department.setDepId(aValue);
		}
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
		if (!(object instanceof ArchivesDep)) {
			return false;
		}
		ArchivesDep rhs = (ArchivesDep) object;
		return new EqualsBuilder().append(this.archDepId, rhs.archDepId).append(this.signNo, rhs.signNo)
				.append(this.subject, rhs.subject).append(this.status, rhs.status).append(this.signTime, rhs.signTime)
				.append(this.signFullname, rhs.signFullname).append(this.handleFeedback, rhs.handleFeedback)
				.append(this.isMain, rhs.isMain).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.archDepId).append(this.signNo)
				.append(this.subject).append(this.status).append(this.signTime).append(this.signFullname)
				.append(this.handleFeedback).append(this.isMain).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("archDepId", this.archDepId).append("signNo", this.signNo)
				.append("subject", this.subject).append("status", this.status).append("signTime", this.signTime)
				.append("signFullname", this.signFullname).append("handleFeedback", this.handleFeedback)
				.append("isMain", this.isMain).toString();
	}
}
