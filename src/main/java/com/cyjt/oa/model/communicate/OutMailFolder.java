package com.cyjt.oa.model.communicate;

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

import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.AppUser;

@Entity
@Table(name = "out_mail_folder")
public class OutMailFolder extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7906588992890470427L;
	private Long folderId;
	private String folderName;
	private Long parentId;
	private Integer depLevel;
	private String path;
	private Short folderType;
	private AppUser appUser;
	private Set<OutMail> outMails = new HashSet<OutMail>();

	public OutMailFolder() {
	}

	public OutMailFolder(Long in_folderId) {
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

	@Column(name = "folderType", nullable = false)
	public Short getFolderType() {
		return this.folderType;
	}

	public void setFolderType(Short folderType) {
		this.folderType = folderType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "outMailFolder")
	public Set<OutMail> getOutMails() {
		return this.outMails;
	}

	public void setOutMails(Set<OutMail> outMails) {
		this.outMails = outMails;
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
		if (!(object instanceof OutMailFolder)) {
			return false;
		}
		OutMailFolder rhs = (OutMailFolder) object;
		return new EqualsBuilder().append(this.folderId, rhs.folderId).append(this.folderName, rhs.folderName)
				.append(this.parentId, rhs.parentId).append(this.depLevel, rhs.depLevel).append(this.path, rhs.path)
				.append(this.folderType, rhs.folderType).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.folderId).append(this.folderName)
				.append(this.parentId).append(this.depLevel).append(this.path).append(this.folderType).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("folderId", this.folderId).append("folderName", this.folderName)
				.append("parentId", this.parentId).append("depLevel", this.depLevel).append("path", this.path)
				.append("folderType", this.folderType).toString();
	}
}
