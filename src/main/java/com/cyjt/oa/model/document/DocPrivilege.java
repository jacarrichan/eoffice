package com.cyjt.oa.model.document;

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
@Table(name = "doc_privilege")
public class DocPrivilege extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5323511498188689954L;
	private Long privilegeId;
	private Integer rights;
	private Integer udrId;
	private String udrName;
	private Short flag;
	private Short fdFlag;
	private Document document;
	private DocFolder docFolder;

	public DocPrivilege() {
	}

	public DocPrivilege(Long in_privilegeId) {
		this.setPrivilegeId(in_privilegeId);
	}

	@Id
	@GeneratedValue
	@Column(name = "privilegeId", unique = true, nullable = false)
	public Long getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(Long privilegeId) {
		this.privilegeId = privilegeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "docId")
	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "folderId")
	public DocFolder getDocFolder() {
		return this.docFolder;
	}

	public void setDocFolder(DocFolder docFolder) {
		this.docFolder = docFolder;
	}

	@Column(name = "rights", nullable = false)
	public Integer getRights() {
		return this.rights;
	}

	public void setRights(Integer rights) {
		this.rights = rights;
	}

	@Column(name = "udrId")
	public Integer getUdrId() {
		return this.udrId;
	}

	public void setUdrId(Integer udrId) {
		this.udrId = udrId;
	}

	@Column(name = "udrName", length = 128)
	public String getUdrName() {
		return this.udrName;
	}

	public void setUdrName(String udrName) {
		this.udrName = udrName;
	}

	@Column(name = "flag", nullable = false)
	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(Short flag) {
		this.flag = flag;
	}

	@Column(name = "fdFlag", nullable = false)
	public Short getFdFlag() {
		return this.fdFlag;
	}

	public void setFdFlag(Short fdFlag) {
		this.fdFlag = fdFlag;
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
	public Long getDocId() {
		return this.getDocument() == null ? null : this.getDocument().getDocId();
	}

	public void setDocId(Long aValue) {
		if (aValue == null) {
			this.document = null;
		} else if (this.document == null) {
			this.document = new Document(aValue);
			this.document.setVersion(new Integer(0));
		} else {
			this.document.setDocId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DocPrivilege)) {
			return false;
		}
		DocPrivilege rhs = (DocPrivilege) object;
		return new EqualsBuilder().append(this.privilegeId, rhs.privilegeId).append(this.rights, rhs.rights)
				.append(this.udrId, rhs.udrId).append(this.udrName, rhs.udrName).append(this.flag, rhs.flag).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.privilegeId).append(this.rights)
				.append(this.udrId).append(this.udrName).append(this.flag).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("privilegeId", this.privilegeId).append("rights", this.rights)
				.append("udrId", this.udrId).append("udrName", this.udrName).append("flag", this.flag).toString();
	}
}
