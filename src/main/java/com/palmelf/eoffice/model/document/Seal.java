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

import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.FileAttach;

@Entity
@Table(name = "seal")
public class Seal extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7653317183781734956L;
	private Long sealId;
	private String sealName;
	private String sealPath;
	private Long belongId;
	private String belongName;
	private FileAttach fileAttach;

	public Seal() {
	}

	public Seal(Long in_sealId) {
		this.setSealId(in_sealId);
	}

	@Id
	@GeneratedValue
	@Column(name = "sealId", unique = true, nullable = false)
	public Long getSealId() {
		return this.sealId;
	}

	public void setSealId(Long sealId) {
		this.sealId = sealId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fileId")
	public FileAttach getFileAttach() {
		return this.fileAttach;
	}

	public void setFileAttach(FileAttach fileAttach) {
		this.fileAttach = fileAttach;
	}

	@Column(name = "sealName", nullable = false, length = 64)
	public String getSealName() {
		return this.sealName;
	}

	public void setSealName(String sealName) {
		this.sealName = sealName;
	}

	@Column(name = "sealPath", length = 128)
	public String getSealPath() {
		return this.sealPath;
	}

	public void setSealPath(String sealPath) {
		this.sealPath = sealPath;
	}

	@Column(name = "belongId")
	public Long getBelongId() {
		return this.belongId;
	}

	public void setBelongId(Long belongId) {
		this.belongId = belongId;
	}

	@Column(name = "belongName", nullable = false, length = 64)
	public String getBelongName() {
		return this.belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
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
		if (!(object instanceof Seal)) {
			return false;
		}
		Seal rhs = (Seal) object;
		return new EqualsBuilder().append(this.sealId, rhs.sealId).append(this.sealName, rhs.sealName)
				.append(this.sealPath, rhs.sealPath).append(this.belongId, rhs.belongId)
				.append(this.belongName, rhs.belongName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.sealId).append(this.sealName)
				.append(this.sealPath).append(this.belongId).append(this.belongName).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("sealId", this.sealId).append("sealName", this.sealName)
				.append("sealPath", this.sealPath).append("belongId", this.belongId)
				.append("belongName", this.belongName).toString();
	}
}
