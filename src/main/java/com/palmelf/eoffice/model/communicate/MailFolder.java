package com.palmelf.eoffice.model.communicate;

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
import com.palmelf.eoffice.model.system.AppUser;
@Entity
@Table(name = "mail_folder")
public class MailFolder extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6829892576057306049L;
	private Long folderId;
	private String folderName;
	private Long parentId;
	private Integer depLevel;
	private String path;
	private Short isPublic;
	private Short folderType;
	private AppUser appUser;

	public MailFolder() {
	}

	public MailFolder(Long in_folderId) {
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

	@Column(name = "depLevel", nullable = false)
	public Integer getDepLevel() {
		return this.depLevel;
	}

	public void setDepLevel(Integer depLevel) {
		this.depLevel = depLevel;
	}

	@Column(name = "path", length = 256)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "isPublic", nullable = false)
	public Short getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Short isPublic) {
		this.isPublic = isPublic;
	}

	@Column(name = "folderType", nullable = false)
	public Short getFolderType() {
		return this.folderType;
	}

	public void setFolderType(Short folderType) {
		this.folderType = folderType;
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
		if (!(object instanceof MailFolder)) {
			return false;
		}
		MailFolder rhs = (MailFolder) object;
		return new EqualsBuilder().append(this.folderId, rhs.folderId).append(this.folderName, rhs.folderName)
				.append(this.parentId, rhs.parentId).append(this.depLevel, rhs.depLevel)
				.append(this.isPublic, rhs.isPublic).append(this.folderType, rhs.folderType).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.folderId).append(this.folderName)
				.append(this.parentId).append(this.depLevel).append(this.isPublic).append(this.folderType).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("folderId", this.folderId).append("folderName", this.folderName)
				.append("parentId", this.parentId).append("depLevel", this.depLevel).append("isPublic", this.isPublic)
				.append("folderType", this.folderType).toString();
	}

}
