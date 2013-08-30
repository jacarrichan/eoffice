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

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "archives_handle")
public class ArchivesHandle extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2278805105421860272L;

	@Expose
	private Long handleId;

	@Expose
	private String handleOpinion;

	@Expose
	private Long userId;

	@Expose
	private String userFullname;

	@Expose
	private Date createtime;

	@Expose
	private Date fillTime;

	@Expose
	private Short isPass;
	private Archives archives;

	public ArchivesHandle() {
	}

	public ArchivesHandle(Long in_handleId) {
		this.setHandleId(in_handleId);
	}
	@Transient
	public Long getArchivesId() {
		return this.getArchives() == null ? null : this.getArchives().getArchivesId();
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "handleId", unique = true, nullable = false)
	public Long getHandleId() {
		return this.handleId;
	}

	public void setHandleId(Long handleId) {
		this.handleId = handleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "archivesId")
	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	@Column(name = "handleOpinion", length = 1024)
	public String getHandleOpinion() {
		return this.handleOpinion;
	}

	public void setHandleOpinion(String handleOpinion) {
		this.handleOpinion = handleOpinion;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "userFullname", nullable = false, length = 128)
	public String getUserFullname() {
		return this.userFullname;
	}

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "fillTime", length = 19)
	public Date getFillTime() {
		return this.fillTime;
	}

	public void setFillTime(Date fillTime) {
		this.fillTime = fillTime;
	}

	@Column(name = "isPass", nullable = false)
	public Short getIsPass() {
		return this.isPass;
	}

	public void setIsPass(Short isPass) {
		this.isPass = isPass;
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
		if (!(object instanceof ArchivesHandle)) {
			return false;
		}
		ArchivesHandle rhs = (ArchivesHandle) object;
		return new EqualsBuilder().append(this.handleId, rhs.handleId).append(this.handleOpinion, rhs.handleOpinion)
				.append(this.userId, rhs.userId).append(this.userFullname, rhs.userFullname)
				.append(this.createtime, rhs.createtime).append(this.fillTime, rhs.fillTime)
				.append(this.isPass, rhs.isPass).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.handleId).append(this.handleOpinion)
				.append(this.userId).append(this.userFullname).append(this.createtime).append(this.fillTime)
				.append(this.isPass).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("handleId", this.handleId).append("handleOpinion", this.handleOpinion)
				.append("userId", this.userId).append("userFullname", this.userFullname)
				.append("createtime", this.createtime).append("fillTime", this.fillTime).append("isPass", this.isPass)
				.toString();
	}
}
