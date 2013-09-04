package com.palmelf.eoffice.model.archive;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.FileAttach;

@Entity
@Table(name = "doc_history")
public class DocHistory extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5676912523256944849L;
	private Long historyId;
	private String docName;
	private String path;
	private Integer version;
	private Date updatetime;
	private String mender;
	private FileAttach fileAttach;
	private ArchivesDoc archivesDoc;

	public DocHistory() {
	}

	public DocHistory(Long in_historyId) {
		this.setHistoryId(in_historyId);
	}

	@Transient
	public Long getDocId() {
		return this.getArchivesDoc() == null ? null : this.getArchivesDoc().getDocId();
	}

	public void setDocId(Long aValue) {
		if (aValue == null) {
			this.archivesDoc = null;
		} else if (this.archivesDoc == null) {
			this.archivesDoc = new ArchivesDoc(aValue);
			this.archivesDoc.setVersion(new Integer(0));
		} else {
			this.archivesDoc.setDocId(aValue);
		}
	}

	@Transient
	public Long getFileId() {
		return this.getFileAttach() == null ? null : this.getFileAttach().getFileId();
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "historyId", unique = true, nullable = false)
	public Long getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
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
	@JoinColumn(name = "docId")
	public ArchivesDoc getArchivesDoc() {
		return this.archivesDoc;
	}

	public void setArchivesDoc(ArchivesDoc archivesDoc) {
		this.archivesDoc = archivesDoc;
	}

	@Column(name = "docName", nullable = false, length = 128)
	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	@Column(name = "path", nullable = false, length = 128)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "updatetime", nullable = false, length = 19)
	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "mender", nullable = false, length = 64)
	public String getMender() {
		return this.mender;
	}

	public void setMender(String mender) {
		this.mender = mender;
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
	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DocHistory)) {
			return false;
		}
		DocHistory rhs = (DocHistory) object;
		return new EqualsBuilder().append(this.historyId, rhs.historyId).append(this.docName, rhs.docName)
				.append(this.path, rhs.path).append(this.version, rhs.version).append(this.updatetime, rhs.updatetime)
				.append(this.mender, rhs.mender).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.historyId).append(this.docName).append(this.path)
				.append(this.version).append(this.updatetime).append(this.mender).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("historyId", this.historyId).append("docName", this.docName)
				.append("path", this.path).append("version", this.version).append("updatetime", this.updatetime)
				.append("mender", this.mender).toString();
	}
}
