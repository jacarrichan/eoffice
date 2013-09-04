package com.palmelf.eoffice.model.document;

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
import com.palmelf.eoffice.model.system.AppUser;

@Entity
@Table(name = "doc_folder")
public class DocFolder extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7955586088376349799L;
	public static final Short IS_SHARED = 1;
	public static final Short IS_NOT_SHARED = 0;

	@Expose
	private Long folderId;

	@Expose
	private String folderName;

	@Expose
	private Long parentId;

	@Expose
	private String path;

	@Expose
	private Short isShared;

	@Expose
	private AppUser appUser;

	public DocFolder() {
	}

	public DocFolder(Long in_folderId) {
		this.setFolderId(in_folderId);
	}

	@Id
	@GeneratedValue
	@Column(name = "folderId", unique = true, nullable = false)
	public Long getFolderId() {
		return this.folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "folderName", nullable = false, length = 128)
	public String getFolderName() {
		return this.folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	@Column(name = "parentId")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "path", length = 128)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "isShared", nullable = false)
	public Short getIsShared() {
		return this.isShared;
	}

	public void setIsShared(Short isShared) {
		this.isShared = isShared;
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
		if (!(object instanceof DocFolder)) {
			return false;
		}
		DocFolder rhs = (DocFolder) object;
		return new EqualsBuilder().append(this.folderId, rhs.folderId).append(this.folderName, rhs.folderName)
				.append(this.parentId, rhs.parentId).append(this.path, rhs.path).append(this.isShared, rhs.isShared)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.folderId).append(this.folderName)
				.append(this.parentId).append(this.path).append(this.isShared).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("folderId", this.folderId).append("folderName", this.folderName)
				.append("parentId", this.parentId).append("path", this.path).append("isShared", this.isShared)
				.toString();
	}

}
