package com.cyjt.oa.model.arch;

import java.util.Date;
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

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.GlobalType;

@Entity
@Table(name = "roll_file")
public class RollFile extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2275580192595930378L;

	@Expose
	private Long rollFileId;

	@Expose
	private String typeName;

	@Expose
	private String fileName;

	@Expose
	private String fileNo;

	@Expose
	private String dutyPerson;

	@Expose
	private String afNo;

	@Expose
	private String catNo;

	@Expose
	private String rollNo;

	@Expose
	private Integer seqNo;

	@Expose
	private Integer pageNo;

	@Expose
	private Integer pageNums;

	@Expose
	private String secretLevel;

	@Expose
	private String timeLimit;

	@Expose
	private String openStyle;

	@Expose
	private String keyWords;

	@Expose
	private String notes;

	@Expose
	private String content;

	@Expose
	private Date fileTime;

	@Expose
	private String creatorName;

	@Expose
	private Date createTime;

	@Expose
	private Short archStatus;

	@Expose
	private ArchRoll archRoll;

	@Expose
	private GlobalType globalType;

	@Expose
	private Set<BorrowFileList> borrowFileList = new HashSet<BorrowFileList>();

	@Expose
	private Set<RollFileList> rollFileLists = new HashSet<RollFileList>();

	@Expose
	private String tidyName;

	@Expose
	private Date tidyTime;

	public RollFile() {
	}

	public RollFile(Long in_rollFileId) {
		this.setRollFileId(in_rollFileId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "rollFileId", unique = true, nullable = false)
	public Long getRollFileId() {
		return this.rollFileId;
	}

	public void setRollFileId(Long rollFileId) {
		this.rollFileId = rollFileId;
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
	@JoinColumn(name = "proTypeId")
	public GlobalType getGlobalType() {
		return this.globalType;
	}

	public void setGlobalType(GlobalType globalType) {
		this.globalType = globalType;
	}

	@Column(name = "typeName", length = 128)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "fileName", nullable = false, length = 128)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "fileNo", nullable = false, length = 64)
	public String getFileNo() {
		return this.fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	@Column(name = "dutyPerson", length = 32)
	public String getDutyPerson() {
		return this.dutyPerson;
	}

	public void setDutyPerson(String dutyPerson) {
		this.dutyPerson = dutyPerson;
	}

	@Column(name = "afNo", length = 64)
	public String getAfNo() {
		return this.afNo;
	}

	public void setAfNo(String afNo) {
		this.afNo = afNo;
	}

	@Column(name = "catNo", length = 64)
	public String getCatNo() {
		return this.catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	@Column(name = "rollNo", length = 64)
	public String getRollNo() {
		return this.rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	@Column(name = "seqNo")
	public Integer getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	@Column(name = "pageNo")
	public Integer getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	@Column(name = "pageNums")
	public Integer getPageNums() {
		return this.pageNums;
	}

	public void setPageNums(Integer pageNums) {
		this.pageNums = pageNums;
	}

	@Column(name = "secretLevel", length = 64)
	public String getSecretLevel() {
		return this.secretLevel;
	}

	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}

	@Column(name = "timeLimit", length = 64)
	public String getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Column(name = "openStyle", length = 64)
	public String getOpenStyle() {
		return this.openStyle;
	}

	public void setOpenStyle(String openStyle) {
		this.openStyle = openStyle;
	}

	@Column(name = "keyWords", length = 512)
	public String getKeyWords() {
		return this.keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	@Column(name = "notes", length = 4000)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "fileTime", length = 19)
	public Date getFileTime() {
		return this.fileTime;
	}

	public void setFileTime(Date fileTime) {
		this.fileTime = fileTime;
	}

	@Column(name = "creatorName", length = 128)
	public String getCreatorName() {
		return this.creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "archStatus")
	public Short getArchStatus() {
		return this.archStatus;
	}

	public void setArchStatus(Short archStatus) {
		this.archStatus = archStatus;
	}

	@Column(name = "tidyName", length = 128)
	public String getTidyName() {
		return this.tidyName;
	}

	public void setTidyName(String tidyName) {
		this.tidyName = tidyName;
	}

	@Column(name = "tidyTime", length = 19)
	public Date getTidyTime() {
		return this.tidyTime;
	}

	public void setTidyTime(Date tidyTime) {
		this.tidyTime = tidyTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rollFile")
	public Set<RollFileList> getRollFileLists() {
		return this.rollFileLists;
	}

	public void setRollFileLists(Set<RollFileList> rollFileLists) {
		this.rollFileLists = rollFileLists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rollFile")
	public Set<BorrowFileList> getBorrowFileList() {
		return this.borrowFileList;
	}

	public void setBorrowFileList(Set<BorrowFileList> borrowFileLists) {
		this.borrowFileList = borrowFileLists;
	}

	@Transient
	public Long getProTypeId() {
		return this.getGlobalType() == null ? null : this.getGlobalType()
				.getProTypeId();
	}

	public void setProTypeId(Long aValue) {
		if (aValue == null) {
			this.globalType = null;
		} else if (this.globalType == null) {
			this.globalType = new GlobalType(aValue);
			this.globalType.setVersion(new Integer(0));
		} else {
			this.globalType.setProTypeId(aValue);
		}
	}

	@Transient
	public Long getRollId() {
		return this.getArchRoll() == null ? null : this.getArchRoll()
				.getRollId();
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

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof RollFile)) {
			return false;
		}
		RollFile rhs = (RollFile) object;
		return new EqualsBuilder().append(this.rollFileId, rhs.rollFileId)
				.append(this.typeName, rhs.typeName)
				.append(this.fileName, rhs.fileName)
				.append(this.fileNo, rhs.fileNo)
				.append(this.dutyPerson, rhs.dutyPerson)
				.append(this.afNo, rhs.afNo).append(this.catNo, rhs.catNo)
				.append(this.rollNo, rhs.rollNo).append(this.seqNo, rhs.seqNo)
				.append(this.pageNo, rhs.pageNo)
				.append(this.pageNums, rhs.pageNums)
				.append(this.secretLevel, rhs.secretLevel)
				.append(this.timeLimit, rhs.timeLimit)
				.append(this.openStyle, rhs.openStyle)
				.append(this.keyWords, rhs.keyWords)
				.append(this.notes, rhs.notes)
				.append(this.content, rhs.content)
				.append(this.fileTime, rhs.fileTime)
				.append(this.creatorName, rhs.creatorName)
				.append(this.createTime, rhs.createTime)
				.append(this.archStatus, rhs.archStatus).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.rollFileId).append(this.typeName)
				.append(this.fileName).append(this.fileNo)
				.append(this.dutyPerson).append(this.afNo).append(this.catNo)
				.append(this.rollNo).append(this.seqNo).append(this.pageNo)
				.append(this.pageNums).append(this.secretLevel)
				.append(this.timeLimit).append(this.openStyle)
				.append(this.keyWords).append(this.notes).append(this.content)
				.append(this.fileTime).append(this.creatorName)
				.append(this.createTime).append(this.archStatus).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("rollFileId", this.rollFileId)
				.append("typeName", this.typeName)
				.append("fileName", this.fileName)
				.append("fileNo", this.fileNo)
				.append("dutyPerson", this.dutyPerson)
				.append("afNo", this.afNo).append("catNo", this.catNo)
				.append("rollNo", this.rollNo).append("seqNo", this.seqNo)
				.append("pageNo", this.pageNo)
				.append("pageNums", this.pageNums)
				.append("secretLevel", this.secretLevel)
				.append("timeLimit", this.timeLimit)
				.append("openStyle", this.openStyle)
				.append("keyWords", this.keyWords).append("notes", this.notes)
				.append("content", this.content)
				.append("fileTime", this.fileTime)
				.append("creatorName", this.creatorName)
				.append("createTime", this.createTime)
				.append("archStatus", this.archStatus).toString();
	}
}
