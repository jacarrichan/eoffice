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

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "borrow_file_list")
public class BorrowFileList extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4199271145814803298L;

	@Expose
	private Long listId;

	@Expose
	private String listType;

	@Expose
	private String afNo;

	@Expose
	private String afName;

	@Expose
	private String rollNo;

	@Expose
	private String rolllName;

	@Expose
	private String fileNo;

	@Expose
	private String fileName;

	@Expose
	private RollFile rollFile;

	@Expose
	private ArchRoll archRoll;

	@Expose
	private ArchFond archFond;

	@Expose
	private BorrowRecord borrowRecord;

	public BorrowFileList() {
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
	@JoinColumn(name = "rollId")
	public ArchRoll getArchRoll() {
		return this.archRoll;
	}

	public void setArchRoll(ArchRoll archRoll) {
		this.archRoll = archRoll;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "archFondId")
	public ArchFond getArchFond() {
		return this.archFond;
	}

	public void setArchFond(ArchFond archFond) {
		this.archFond = archFond;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recordId")
	public BorrowRecord getBorrowRecord() {
		return this.borrowRecord;
	}

	public void setBorrowRecord(BorrowRecord borrowRecord) {
		this.borrowRecord = borrowRecord;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rollFileId")
	public RollFile getRollFile() {
		return this.rollFile;
	}

	public void setRollFile(RollFile rollFile) {
		this.rollFile = rollFile;
	}

	@Column(name = "listType", length = 64)
	public String getListType() {
		return this.listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	@Column(name = "afNo", length = 64)
	public String getAfNo() {
		return this.afNo;
	}

	public void setAfNo(String afNo) {
		this.afNo = afNo;
	}

	@Column(name = "afName", length = 128)
	public String getAfName() {
		return this.afName;
	}

	public void setAfName(String afName) {
		this.afName = afName;
	}

	@Column(name = "rollNo", length = 64)
	public String getRollNo() {
		return this.rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	@Column(name = "rolllName", length = 128)
	public String getRolllName() {
		return this.rolllName;
	}

	public void setRolllName(String rolllName) {
		this.rolllName = rolllName;
	}

	@Column(name = "fileNo", length = 64)
	public String getFileNo() {
		return this.fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	@Column(name = "fileName", length = 128)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Transient
	public Long getRecordId() {
		return getBorrowRecord() == null ? null : getBorrowRecord()
				.getRecordId();
	}

	public void setRecordId(Long aValue) {
		if (aValue == null) {
			this.borrowRecord = null;
		} else if (this.borrowRecord == null) {
			this.borrowRecord = new BorrowRecord(aValue);
			this.borrowRecord.setVersion(new Integer(0));
		} else {
			this.borrowRecord.setRecordId(aValue);
		}
	}
	@Transient
	public Long getArchFondId() {
		return getArchFond() == null ? null : getArchFond().getArchFondId();
	}

	public void setArchFondId(Long aValue) {
		if (aValue == null) {
			this.archFond = null;
		} else if (this.archFond == null) {
			this.archFond = new ArchFond(aValue);
			this.archFond.setVersion(new Integer(0));
		} else {
			this.archFond.setArchFondId(aValue);
		}
	}
	@Transient
	public Long getRollId() {
		return getArchRoll() == null ? null : getArchRoll().getRollId();
	}

	public void setRollId(Long aValue) {
		if (aValue == null) {
			this.archRoll = null;
		} else if (this.archRoll == null) {
			this.archRoll = new ArchRoll(aValue);
			this.archRoll.setVersion(new Integer(0));
		} else {
			this.archRoll.setRollId(aValue);
		}
	}
	@Transient
	public Long getRollFileId() {
		return getRollFile() == null ? null : getRollFile().getRollFileId();
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
		if (!(object instanceof BorrowFileList)) {
			return false;
		}
		BorrowFileList rhs = (BorrowFileList) object;
		return new EqualsBuilder().append(this.listId, rhs.listId).append(this.listType, rhs.listType)
				.append(this.afNo, rhs.afNo).append(this.afName, rhs.afName).append(this.rollNo, rhs.rollNo)
				.append(this.rolllName, rhs.rolllName).append(this.fileNo, rhs.fileNo)
				.append(this.fileName, rhs.fileName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.listId).append(this.listType).append(this.afNo)
				.append(this.afName).append(this.rollNo).append(this.rolllName).append(this.fileNo)
				.append(this.fileName).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("listId", this.listId).append("listType", this.listType)
				.append("afNo", this.afNo).append("afName", this.afName).append("rollNo", this.rollNo)
				.append("rolllName", this.rolllName).append("fileNo", this.fileNo).append("fileName", this.fileName)
				.toString();
	}
}
