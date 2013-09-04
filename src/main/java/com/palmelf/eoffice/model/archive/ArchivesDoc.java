package com.palmelf.eoffice.model.archive;

import java.util.Date;
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
import com.palmelf.eoffice.model.system.FileAttach;

@Entity
@Table(name = "archives_doc")
public class ArchivesDoc extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6751670086699671559L;
	public static short STATUS_MODIFY = 0;
	public static short STATUS_MODIFY_END = 1;
	public static int ORI_VERSION = 1;

	@Expose
	private Long docId;

	@Expose
	private String creator;

	@Expose
	private Long creatorId;

	@Expose
	private Long menderId;

	@Expose
	private String mender;

	@Expose
	private String docName;

	@Expose
	private Short docStatus;

	@Expose
	private Integer curVersion;

	@Expose
	private String docPath;

	@Expose
	private Date updatetime;

	@Expose
	private Date createtime;

	@Expose
	private FileAttach fileAttach;
	private Archives archives;
	private Set<DocHistory> docHistorys = new HashSet<DocHistory>(0);

	public ArchivesDoc() {
	}

	public void initUsers(AppUser curUser) {
		this.setCreator(curUser.getFullname());
		this.setCreatorId(curUser.getUserId());

		this.setMender(curUser.getFullname());
		this.setMenderId(curUser.getUserId());
	}

	public ArchivesDoc(Long in_docId) {
		this.setDocId(in_docId);
	}
	@Transient
	public Long getFileId() {
		return this.getFileAttach() == null ? null : this.getFileAttach().getFileId();
	}

	public void setFileId(Long aValue) {
		if (aValue == null) {
			this.fileAttach = null;
		} else if (this.fileAttach == null) {
			this.fileAttach = new FileAttach(aValue);
			this.fileAttach.setVersion(new Integer(0));
		} else {
			this.fileAttach.setFileId(aValue);
		}
	}
	@Transient
	public Long getArchivesId() {
		return this.getArchives() == null ? null : this.getArchives().getArchivesId();
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "docId", unique = true, nullable = false)
	public Long getDocId() {
		return this.docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fileId")
	public FileAttach getFileAttach() {
		return this.fileAttach;
	}

	public void setFileAttach(FileAttach fileAttach) {
		this.fileAttach = fileAttach;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "archivesId")
	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	@Column(name = "creator", length = 64)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "creatorId")
	public Long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	@Column(name = "menderId")
	public Long getMenderId() {
		return this.menderId;
	}

	public void setMenderId(Long menderId) {
		this.menderId = menderId;
	}

	@Column(name = "mender", length = 64)
	public String getMender() {
		return this.mender;
	}

	public void setMender(String mender) {
		this.mender = mender;
	}

	@Column(name = "docName", nullable = false, length = 128)
	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	@Column(name = "docStatus", nullable = false)
	public Short getDocStatus() {
		return this.docStatus;
	}

	public void setDocStatus(Short docStatus) {
		this.docStatus = docStatus;
	}

	@Column(name = "curVersion", nullable = false)
	public Integer getCurVersion() {
		return this.curVersion;
	}

	public void setCurVersion(Integer curVersion) {
		this.curVersion = curVersion;
	}

	@Column(name = "docPath", nullable = false, length = 128)
	public String getDocPath() {
		return this.docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	@Column(name = "updatetime", nullable = false, length = 19)
	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archivesDoc")
	public Set<DocHistory> getDocHistorys() {
		return docHistorys;
	}

	public void setDocHistorys(Set<DocHistory> docHistorys) {
		this.docHistorys = docHistorys;
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
		if (!(object instanceof ArchivesDoc)) {
			return false;
		}
		ArchivesDoc rhs = (ArchivesDoc) object;
		return new EqualsBuilder().append(this.docId, rhs.docId).append(this.creator, rhs.creator)
				.append(this.creatorId, rhs.creatorId).append(this.menderId, rhs.menderId)
				.append(this.mender, rhs.mender).append(this.docName, rhs.docName)
				.append(this.docStatus, rhs.docStatus).append(this.curVersion, rhs.curVersion)
				.append(this.docPath, rhs.docPath).append(this.updatetime, rhs.updatetime)
				.append(this.createtime, rhs.createtime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.docId).append(this.creator)
				.append(this.creatorId).append(this.menderId).append(this.mender).append(this.docName)
				.append(this.docStatus).append(this.curVersion).append(this.docPath).append(this.updatetime)
				.append(this.createtime).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("docId", this.docId).append("creator", this.creator)
				.append("creatorId", this.creatorId).append("menderId", this.menderId).append("mender", this.mender)
				.append("docName", this.docName).append("docStatus", this.docStatus)
				.append("curVersion", this.curVersion).append("docPath", this.docPath)
				.append("updatetime", this.updatetime).append("createtime", this.createtime).toString();
	}
}
