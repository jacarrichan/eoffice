package com.cyjt.oa.model.archive;

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
import com.cyjt.oa.model.system.FileAttach;

@Entity
@Table(name = "arch_template")
public class ArchTemplate extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6181628259482348428L;
	private Long templateId;
	private String tempName;
	private String tempPath;
	private FileAttach fileAttach;
	private ArchivesType archivesType;

	public ArchTemplate() {
	}

	public ArchTemplate(Long in_templateId) {
		this.setTemplateId(in_templateId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "templateId", unique = true, nullable = false)
	public Long getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
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
	@JoinColumn(name = "typeId")
	public ArchivesType getArchivesType() {
		return this.archivesType;
	}

	public void setArchivesType(ArchivesType archivesType) {
		this.archivesType = archivesType;
	}

	@Column(name = "tempName", nullable = false, length = 128)
	public String getTempName() {
		return this.tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	@Column(name = "tempPath", nullable = false, length = 256)
	public String getTempPath() {
		return this.tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}
	
	@Transient
	public Long getTypeId() {
		return getArchivesType() == null ? null : getArchivesType().getTypeId();
	}
	
	public void setTypeId(Long aValue) {
		if (aValue == null) {
			this.archivesType = null;
		} else if (this.archivesType == null) {
			this.archivesType = new ArchivesType(aValue);
			this.archivesType.setVersion(new Integer(0));
		} else {
			this.archivesType.setTypeId(aValue);
		}
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

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ArchTemplate)) {
			return false;
		}
		ArchTemplate rhs = (ArchTemplate) object;
		return new EqualsBuilder().append(this.templateId, rhs.templateId).append(this.tempName, rhs.tempName)
				.append(this.tempPath, rhs.tempPath).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.templateId).append(this.tempName)
				.append(this.tempPath).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("templateId", this.templateId).append("tempName", this.tempName)
				.append("tempPath", this.tempPath).toString();
	}
}
