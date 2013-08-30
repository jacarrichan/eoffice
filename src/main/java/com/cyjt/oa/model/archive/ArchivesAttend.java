package com.cyjt.oa.model.archive;

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

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "archives_attend")
public class ArchivesAttend extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 17780535133432816L;
	private Long attendId;
	private Long userID;
	private String fullname;
	private String attendType;
	private Date executeTime;
	private String memo;
	private Archives archives;

	public ArchivesAttend() {
	}

	public ArchivesAttend(Long in_attendId) {
		this.setAttendId(in_attendId);
	}
	@Transient
	public Long getArchivesId() {
		return this.getArchives() == null ? null : this.getArchives().getArchivesId();
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "attendId", unique = true, nullable = false)
	public Long getAttendId() {
		return this.attendId;
	}

	public void setAttendId(Long attendId) {
		this.attendId = attendId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "archivesId")
	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	@Column(name = "userID", nullable = false)
	public Long getUserID() {
		return this.userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	@Column(name = "fullname", nullable = false, length = 128)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "attendType", nullable = false, length = 64)
	public String getAttendType() {
		return this.attendType;
	}

	public void setAttendType(String attendType) {
		this.attendType = attendType;
	}

	@Column(name = "executeTime", length = 19)
	public Date getExecuteTime() {
		return this.executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	@Column(name = "memo", length = 1024)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
		if (!(object instanceof ArchivesAttend)) {
			return false;
		}
		ArchivesAttend rhs = (ArchivesAttend) object;
		return new EqualsBuilder().append(this.attendId, rhs.attendId).append(this.userID, rhs.userID)
				.append(this.fullname, rhs.fullname).append(this.attendType, rhs.attendType)
				.append(this.executeTime, rhs.executeTime).append(this.memo, rhs.memo).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.attendId).append(this.userID)
				.append(this.fullname).append(this.attendType).append(this.executeTime).append(this.memo).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("attendId", this.attendId).append("userID", this.userID)
				.append("fullname", this.fullname).append("attendType", this.attendType)
				.append("executeTime", this.executeTime).append("memo", this.memo).toString();
	}
}
