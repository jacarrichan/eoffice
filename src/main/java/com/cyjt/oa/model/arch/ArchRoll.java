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
import javax.persistence.Lob;
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
@Table(name = "arch_roll")
public class ArchRoll extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8777140029502697425L;

	@Expose
	protected Integer fileNums;

	@Expose
	protected Long archFondId;

	@Expose
	protected Long rollId;

	@Expose
	protected String typeName;

	@Expose
	protected String rolllName;

	@Expose
	protected String afNo;

	@Expose
	protected String rollNo;

	@Expose
	protected String catNo;

	@Expose
	protected String timeLimit;

	@Expose
	protected Date startTime;

	@Expose
	protected Date endTime;

	@Expose
	protected String openStyle;

	@Expose
	protected String author;

	@Expose
	protected Date setupTime;

	@Expose
	protected String checker;

	@Expose
	protected String creatorName;

	@Expose
	protected Date createTime;

	@Expose
	protected String keyWords;

	@Expose
	protected String editCompany;

	@Expose
	protected String editDep;

	@Expose
	protected String decp;

	@Expose
	protected Short status;

	@Expose
	protected ArchFond archFond;

	@Expose
	protected GlobalType globalType;

	@Expose
	protected Set<BorrowFileList> borrowFileList = new HashSet<BorrowFileList>();

	@Expose
	protected Set<RollFile> rollFiles = new HashSet<RollFile>();

	public ArchRoll() {
	}

	public ArchRoll(Long in_rollId) {
		this.setRollId(in_rollId);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "archFondId")
	public ArchFond getArchFond() {
		return this.archFond;
	}

	public void setArchFond(ArchFond in_archFond) {
		this.archFond = in_archFond;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proTypeId")
	public GlobalType getGlobalType() {
		return this.globalType;
	}

	public void setGlobalType(GlobalType in_globalType) {
		this.globalType = in_globalType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archRoll")
	public Set<RollFile> getRollFiles() {
		return this.rollFiles;
	}

	public void setRollFiles(Set<RollFile> in_rollFiles) {
		this.rollFiles = in_rollFiles;
	}

	@Id
	@GeneratedValue
	@Column(name = "rollId", unique = true, nullable = false)
	public Long getRollId() {
		return this.rollId;
	}

	public void setRollId(Long aValue) {
		this.rollId = aValue;
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
	public Long getArchFondId() {
		return this.getArchFond() == null ? null : this.getArchFond()
				.getArchFondId();
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

	@Column(name = "typeName", length = 32)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String aValue) {
		this.typeName = aValue;
	}

	@Column(name = "rolllName", nullable = false, length = 128)
	public String getRolllName() {
		return this.rolllName;
	}

	public void setRolllName(String aValue) {
		this.rolllName = aValue;
	}

	@Column(name = "afNo", nullable = false, length = 64)
	public String getAfNo() {
		return this.afNo;
	}

	public void setAfNo(String aValue) {
		this.afNo = aValue;
	}

	@Column(name = "rollNo", nullable = false, length = 64)
	public String getRollNo() {
		return this.rollNo;
	}

	public void setRollNo(String aValue) {
		this.rollNo = aValue;
	}

	@Column(name = "catNo", length = 64)
	public String getCatNo() {
		return this.catNo;
	}

	public void setCatNo(String aValue) {
		this.catNo = aValue;
	}

	@Column(name = "timeLimit", length = 64)
	public String getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(String aValue) {
		this.timeLimit = aValue;
	}

	@Column(name = "startTime", length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date aValue) {
		this.startTime = aValue;
	}

	@Column(name = "endTime", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date aValue) {
		this.endTime = aValue;
	}

	@Column(name = "openStyle", length = 64)
	public String getOpenStyle() {
		return this.openStyle;
	}

	public void setOpenStyle(String aValue) {
		this.openStyle = aValue;
	}

	@Column(name = "author", length = 32)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String aValue) {
		this.author = aValue;
	}

	@Column(name = "setupTime", length = 19)
	public Date getSetupTime() {
		return this.setupTime;
	}

	public void setSetupTime(Date aValue) {
		this.setupTime = aValue;
	}

	@Column(name = "checker", length = 32)
	public String getChecker() {
		return this.checker;
	}

	public void setChecker(String aValue) {
		this.checker = aValue;
	}

	@Column(name = "editCompany", length = 128)
	public String getEditCompany() {
		return this.editCompany;
	}

	public void setEditCompany(String aValue) {
		this.editCompany = aValue;
	}

	@Column(name = "editDep", length = 128)
	public String getEditDep() {
		return this.editDep;
	}

	public void setEditDep(String aValue) {
		this.editDep = aValue;
	}

	@Lob
	@Column(name = "decp")
	public String getDecp() {
		return this.decp;
	}

	public void setDecp(String aValue) {
		this.decp = aValue;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short aValue) {
		this.status = aValue;
	}

	public Integer getFileNums() {
		return this.fileNums;
	}

	public void setFileNums(Integer fileNums) {
		this.fileNums = fileNums;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archRoll")
	public Set<BorrowFileList> getBorrowFileList() {
		return this.borrowFileList;
	}

	public void setBorrowFileList(Set<BorrowFileList> borrowFileList) {
		this.borrowFileList = borrowFileList;
	}

	@Column(name = "creatorName", length = 32)
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

	@Column(name = "keyWords", length = 512)
	public String getKeyWords() {
		return this.keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ArchRoll)) {
			return false;
		}
		ArchRoll rhs = (ArchRoll) object;
		return new EqualsBuilder().append(this.rollId, rhs.rollId)
				.append(this.typeName, rhs.typeName)
				.append(this.rolllName, rhs.rolllName)
				.append(this.afNo, rhs.afNo).append(this.rollNo, rhs.rollNo)
				.append(this.catNo, rhs.catNo)
				.append(this.timeLimit, rhs.timeLimit)
				.append(this.startTime, rhs.startTime)
				.append(this.endTime, rhs.endTime)
				.append(this.openStyle, rhs.openStyle)
				.append(this.author, rhs.author)
				.append(this.setupTime, rhs.setupTime)
				.append(this.checker, rhs.checker)
				.append(this.creatorName, rhs.creatorName)
				.append(this.createTime, rhs.createTime)
				.append(this.keyWords, rhs.keyWords)
				.append(this.editCompany, rhs.editCompany)
				.append(this.editDep, rhs.editDep).append(this.decp, rhs.decp)
				.append(this.status, rhs.status).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.rollId)
				.append(this.typeName).append(this.rolllName).append(this.afNo)
				.append(this.rollNo).append(this.catNo).append(this.timeLimit)
				.append(this.startTime).append(this.endTime)
				.append(this.openStyle).append(this.author)
				.append(this.setupTime).append(this.checker)
				.append(this.creatorName).append(this.createTime)
				.append(this.keyWords).append(this.editCompany)
				.append(this.editDep).append(this.decp).append(this.status)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("rollId", this.rollId)
				.append("typeName", this.typeName)
				.append("rolllName", this.rolllName).append("afNo", this.afNo)
				.append("rollNo", this.rollNo).append("catNo", this.catNo)
				.append("timeLimit", this.timeLimit)
				.append("startTime", this.startTime)
				.append("endTime", this.endTime)
				.append("openStyle", this.openStyle)
				.append("author", this.author)
				.append("setupTime", this.setupTime)
				.append("checker", this.checker)
				.append("creatorName", this.creatorName)
				.append("createTime", this.createTime)
				.append("keyWords", this.keyWords)
				.append("editCompany", this.editCompany)
				.append("editDep", this.editDep).append("decp", this.decp)
				.append("status", this.status).toString();
	}
}
