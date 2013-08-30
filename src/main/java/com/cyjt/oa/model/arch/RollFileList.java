package com.cyjt.oa.model.arch;

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
@Table(name = "roll_file_list")
public class RollFileList extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8852610149989723297L;
	private Long listId;
	private Integer downLoads;
	private Integer sn;
	private String shortDesc;
	private RollFile rollFile;
	private FileAttach fileAttach;

	@Transient
	public Long getFileId() {
		return this.getFileAttach().getFileId();
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

	public RollFileList() {
	}

	public RollFileList(Long in_fileId) {
		this.setListId(in_fileId);
	}

	@Transient
	public Long getRollFileId() {
		return this.getRollFile() == null ? null : this.getRollFile()
				.getRollFileId();
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "listId", unique = true, nullable = false)
	public Long getListId() {
		return this.listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
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
	@JoinColumn(name = "rollFileId")
	public RollFile getRollFile() {
		return this.rollFile;
	}

	public void setRollFile(RollFile rollFile) {
		this.rollFile = rollFile;
	}

	@Column(name = "downLoads")
	public Integer getDownLoads() {
		return this.downLoads;
	}

	public void setDownLoads(Integer downLoads) {
		this.downLoads = downLoads;
	}

	@Column(name = "sn")
	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	@Column(name = "shortDesc", length = 500)
	public String getShortDesc() {
		return this.shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public void setRollFileId(Long aValue) {
		if (aValue == null) {
			this.rollFile = null;
		} else if (this.rollFile == null) {
			this.rollFile = new RollFile(aValue);
			this.rollFile.setVersion(new Integer(0));
		} else {
			this.rollFile.setRollFileId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof RollFileList)) {
			return false;
		}
		RollFileList rhs = (RollFileList) object;
		return new EqualsBuilder().append(this.downLoads, rhs.downLoads)
				.append(this.sn, rhs.sn).append(this.shortDesc, rhs.shortDesc)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.downLoads).append(this.sn).append(this.shortDesc)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("downLoads", this.downLoads)
				.append("sn", this.sn).append("shortDesc", this.shortDesc)
				.toString();
	}
}
