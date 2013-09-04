package com.palmelf.eoffice.model.system;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "file_attach")
public class FileAttach extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4593076574400359532L;

	@Expose
	private Long fileId;

	@Expose
	private String fileName;

	@Expose
	private String filePath;

	@Expose
	private Date createtime;

	@Expose
	private String ext;

	@Expose
	private String fileType;

	@Expose
	private String note;

	@Expose
	private String creator;

	@Expose
	private Double totalBytes;

	public FileAttach() {
	}

	public FileAttach(Long in_fileId) {
		this.setFileId(in_fileId);
	}

	@Id
	@GeneratedValue
	@Column(name = "fileId", unique = true, nullable = false)
	public Long getFileId() {
		return this.fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	@Column(name = "fileName", nullable = false, length = 128)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "filePath", nullable = false, length = 128)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "ext", length = 32)
	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Column(name = "fileType", length = 32)
	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "note", length = 1024)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "creator", nullable = false, length = 32)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "totalBytes", nullable = false, precision = 22, scale = 0)
	public Double getTotalBytes() {
		return this.totalBytes;
	}

	public void setTotalBytes(Double totalBytes) {
		this.totalBytes = totalBytes;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof FileAttach)) {
			return false;
		}
		FileAttach rhs = (FileAttach) object;
		return new EqualsBuilder().append(this.fileId, rhs.fileId).append(this.fileName, rhs.fileName)
				.append(this.filePath, rhs.filePath).append(this.createtime, rhs.createtime).append(this.ext, rhs.ext)
				.append(this.fileType, rhs.fileType).append(this.note, rhs.note).append(this.creator, rhs.creator)
				.append(this.totalBytes, rhs.totalBytes).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.fileId).append(this.fileName)
				.append(this.filePath).append(this.createtime).append(this.ext).append(this.fileType).append(this.note)
				.append(this.creator).append(this.totalBytes).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fileId", this.fileId).append("fileName", this.fileName)
				.append("filePath", this.filePath).append("createtime", this.createtime).append("ext", this.ext)
				.append("type", this.fileType).append("note", this.note).append("creator", this.creator)
				.append("totalBytes", this.totalBytes).toString();
	}

}
