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
import com.cyjt.oa.model.system.FileAttach;

@Entity
@Table(name = "paint_template")
public class PaintTemplate extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7484782465898823019L;
	private Long ptemplateId;
	private String templateName;
	private String path;
	private Short isActivate;
	private FileAttach fileAttach;

	public PaintTemplate() {
	}

	public PaintTemplate(Long in_ptemplateId) {
		this.setPtemplateId(in_ptemplateId);
	}

	@Id
	@GeneratedValue
	@Column(name = "ptemplateId", unique = true, nullable = false)
	public Long getPtemplateId() {
		return this.ptemplateId;
	}

	public void setPtemplateId(Long ptemplateId) {
		this.ptemplateId = ptemplateId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fileId")
	public FileAttach getFileAttach() {
		return this.fileAttach;
	}

	public void setFileAttach(FileAttach fileAttach) {
		this.fileAttach = fileAttach;
	}

	@Column(name = "templateName", nullable = false, length = 64)
	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Column(name = "path", length = 128)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "isActivate", nullable = false)
	public Short getIsActivate() {
		return this.isActivate;
	}

	public void setIsActivate(Short isActivate) {
		this.isActivate = isActivate;
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
		if (!(object instanceof PaintTemplate)) {
			return false;
		}
		PaintTemplate rhs = (PaintTemplate) object;
		return new EqualsBuilder().append(this.ptemplateId, rhs.ptemplateId)
				.append(this.templateName, rhs.templateName).append(this.path, rhs.path)
				.append(this.isActivate, rhs.isActivate).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.ptemplateId).append(this.templateName)
				.append(this.path).append(this.isActivate).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("ptemplateId", this.ptemplateId)
				.append("templateName", this.templateName).append("path", this.path)
				.append("isActivate", this.isActivate).toString();
	}
}
