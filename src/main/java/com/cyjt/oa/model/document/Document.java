package com.cyjt.oa.model.document;

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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.FileAttach;

@Entity
@Table(name = "document")
public class Document extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8698062497129732908L;
	public static final Short SHARED = 1;
	public static final Short NOT_SHARED = 0;

	public static final Short HAVE_ATTACH = 1;
	public static final Short NOT_HAVE_ATTACH = 0;

	@Expose
	private Long docId;

	@Expose
	private String docName;

	@Expose
	private String content;

	@Expose
	private Date createtime;

	@Expose
	private Date updatetime;

	@Expose
	private Short haveAttach;

	@Expose
	private String sharedUserIds;

	@Expose
	private String sharedUserNames;

	@Expose
	private String sharedDepIds;

	@Expose
	private String sharedDepNames;

	@Expose
	private String sharedRoleIds;

	@Expose
	private String sharedRoleNames;

	@Expose
	private Short isShared;

	@Expose
	private String fullname;

	@Expose
	private DocFolder docFolder;
	private AppUser appUser;

	@Expose
	private Set<FileAttach> attachFiles = new HashSet<FileAttach>();

	public Document() {
	}

	public Document(Long in_docId) {
		this.setDocId(in_docId);
	}

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
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "folderId")
	public DocFolder getDocFolder() {
		return this.docFolder;
	}

	public void setDocFolder(DocFolder docFolder) {
		this.docFolder = docFolder;
	}

	@Column(name = "docName", nullable = false, length = 100)
	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	@Column(name = "fullname", nullable = false, length = 32)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	@Lob
	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "updatetime", length = 19)
	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "haveAttach")
	public Short getHaveAttach() {
		return this.haveAttach;
	}

	public void setHaveAttach(Short haveAttach) {
		this.haveAttach = haveAttach;
	}

	@Column(name = "sharedUserIds", length = 1000)
	public String getSharedUserIds() {
		return this.sharedUserIds;
	}

	public void setSharedUserIds(String sharedUserIds) {
		this.sharedUserIds = sharedUserIds;
	}

	@Column(name = "sharedUserNames", length = 1000)
	public String getSharedUserNames() {
		return this.sharedUserNames;
	}

	public void setSharedUserNames(String sharedUserNames) {
		this.sharedUserNames = sharedUserNames;
	}

	@Column(name = "sharedDepIds", length = 1000)
	public String getSharedDepIds() {
		return this.sharedDepIds;
	}

	public void setSharedDepIds(String sharedDepIds) {
		this.sharedDepIds = sharedDepIds;
	}

	@Column(name = "sharedDepNames", length = 1000)
	public String getSharedDepNames() {
		return this.sharedDepNames;
	}

	public void setSharedDepNames(String sharedDepNames) {
		this.sharedDepNames = sharedDepNames;
	}

	@Column(name = "sharedRoleIds", length = 1000)
	public String getSharedRoleIds() {
		return this.sharedRoleIds;
	}

	public void setSharedRoleIds(String sharedRoleIds) {
		this.sharedRoleIds = sharedRoleIds;
	}

	@Column(name = "sharedRoleNames", length = 1000)
	public String getSharedRoleNames() {
		return this.sharedRoleNames;
	}

	public void setSharedRoleNames(String sharedRoleNames) {
		this.sharedRoleNames = sharedRoleNames;
	}

	@Column(name = "isShared", nullable = false)
	public Short getIsShared() {
		return this.isShared;
	}

	public void setIsShared(Short isShared) {
		this.isShared = isShared;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "doc_file", joinColumns = { @JoinColumn(name = "docId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "fileId", nullable = false, updatable = false) })
	public Set<FileAttach> getAttachFiles() {
		return this.attachFiles;
	}

	public void setAttachFiles(Set<FileAttach> attachFiles) {
		this.attachFiles = attachFiles;
	}
	@Transient
	public Long getFolderId() {
		return this.getDocFolder() == null ? null : this.getDocFolder().getFolderId();
	}

	public void setFolderId(Long aValue) {
		if (aValue == null) {
			this.docFolder = null;
		} else if (this.docFolder == null) {
			this.docFolder = new DocFolder(aValue);
			this.docFolder.setVersion(new Integer(0));
		} else {
			this.docFolder.setFolderId(aValue);
		}
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
		if (!(object instanceof Document)) {
			return false;
		}
		Document rhs = (Document) object;
		return new EqualsBuilder().append(this.docId, rhs.docId).append(this.docName, rhs.docName)
				.append(this.fullname, rhs.fullname).append(this.content, rhs.content)
				.append(this.createtime, rhs.createtime).append(this.updatetime, rhs.updatetime)
				.append(this.haveAttach, rhs.haveAttach).append(this.sharedUserIds, rhs.sharedUserIds)
				.append(this.sharedDepIds, rhs.sharedDepIds).append(this.sharedRoleIds, rhs.sharedRoleIds)
				.append(this.isShared, rhs.isShared).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.docId).append(this.docName).append(this.content)
				.append(this.createtime).append(this.updatetime).append(this.fullname).append(this.haveAttach)
				.append(this.sharedUserIds).append(this.sharedDepIds).append(this.sharedRoleIds).append(this.isShared)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("docId", this.docId).append("docName", this.docName)
				.append("content", this.content).append("fullname", this.fullname)
				.append("createtime", this.createtime).append("updatetime", this.updatetime)
				.append("haveAttach", this.haveAttach).append("sharedUserIds", this.sharedUserIds)
				.append("sharedDepIds", this.sharedDepIds).append("sharedRoleIds", this.sharedRoleIds)
				.append("isShared", this.isShared).toString();
	}

}
