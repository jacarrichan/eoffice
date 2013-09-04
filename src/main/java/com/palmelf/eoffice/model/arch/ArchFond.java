package com.palmelf.eoffice.model.arch;

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
import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.GlobalType;

@Entity
@Table(name = "arch_fond")
public class ArchFond extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7838262306351441871L;

	@Expose
	protected Long archFondId;

	@Expose
	protected String afNo;

	@Expose
	protected String afName;

	@Expose
	protected String shortDesc;

	@Expose
	protected String descp;

	@Expose
	protected String clearupDesc;

	@Expose
	protected Date createTime;

	@Expose
	protected Date updateTime;

	@Expose
	protected String creatorName;

	@Expose
	protected Long creatorId;

	@Expose
	protected Integer caseNums;

	@Expose
	protected Short status;

	@Expose
	protected String typeName;

	@Expose
	protected String openStyle;

	@Expose
	protected GlobalType globalType;

	@Expose
	protected Set<ArchRoll> archRolls = new HashSet<ArchRoll>();

	@Expose
	protected Set<BorrowFileList> borrowFileList = new HashSet<BorrowFileList>();

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "updateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "creatorName", length = 32)
	public String getCreatorName() {
		return this.creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archFond")
	public Set<BorrowFileList> getBorrowFileList() {
		return this.borrowFileList;
	}

	public void setBorrowFileList(Set<BorrowFileList> borrowFileList) {
		this.borrowFileList = borrowFileList;
	}

	public ArchFond() {
	}

	public ArchFond(Long in_archFondId) {
		this.setArchFondId(in_archFondId);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proTypeId")
	public GlobalType getGlobalType() {
		return this.globalType;
	}

	public void setGlobalType(GlobalType in_globalType) {
		this.globalType = in_globalType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archFond")
	public Set<ArchRoll> getArchRolls() {
		return this.archRolls;
	}

	public void setArchRolls(Set<ArchRoll> in_archRolls) {
		this.archRolls = in_archRolls;
	}

	@Id
	@GeneratedValue
	@Column(name = "archFondId", unique = true, nullable = false)
	public Long getArchFondId() {
		return this.archFondId;
	}

	public void setArchFondId(Long aValue) {
		this.archFondId = aValue;
	}

	@Column(name = "afNo", nullable = false, length = 64)
	public String getAfNo() {
		return this.afNo;
	}

	public void setAfNo(String aValue) {
		this.afNo = aValue;
	}

	@Column(name = "afName", length = 128)
	public String getAfName() {
		return this.afName;
	}

	public void setAfName(String aValue) {
		this.afName = aValue;
	}

	@Lob
	@Column(name = "shortDesc")
	public String getShortDesc() {
		return this.shortDesc;
	}

	public void setShortDesc(String aValue) {
		this.shortDesc = aValue;
	}

	@Lob
	@Column(name = "descp")
	public String getDescp() {
		return this.descp;
	}

	public void setDescp(String aValue) {
		this.descp = aValue;
	}

	@Lob
	@Column(name = "clearupDesc")
	public String getClearupDesc() {
		return this.clearupDesc;
	}

	public void setClearupDesc(String aValue) {
		this.clearupDesc = aValue;
	}

	@Column(name = "creatorId")
	public Long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Long aValue) {
		this.creatorId = aValue;
	}

	@Column(name = "caseNums")
	public Integer getCaseNums() {
		return this.caseNums;
	}

	public void setCaseNums(Integer aValue) {
		this.caseNums = aValue;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short aValue) {
		this.status = aValue;
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

	@Column(name = "typeName", length = 128)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String aValue) {
		this.typeName = aValue;
	}

	@Column(name = "openStyle", length = 64)
	public String getOpenStyle() {
		return this.openStyle;
	}

	public void setOpenStyle(String aValue) {
		this.openStyle = aValue;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ArchFond)) {
			return false;
		}
		ArchFond rhs = (ArchFond) object;
		return new EqualsBuilder().append(this.archFondId, rhs.archFondId)
				.append(this.afNo, rhs.afNo).append(this.afName, rhs.afName)
				.append(this.shortDesc, rhs.shortDesc)
				.append(this.descp, rhs.descp)
				.append(this.clearupDesc, rhs.clearupDesc)
				.append(this.createTime, rhs.createTime)
				.append(this.updateTime, rhs.updateTime)
				.append(this.creatorName, rhs.creatorName)
				.append(this.creatorId, rhs.creatorId)
				.append(this.caseNums, rhs.caseNums)
				.append(this.status, rhs.status)
				.append(this.typeName, rhs.typeName)
				.append(this.openStyle, rhs.openStyle).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.archFondId).append(this.afNo).append(this.afName)
				.append(this.shortDesc).append(this.descp)
				.append(this.clearupDesc).append(this.createTime)
				.append(this.updateTime).append(this.creatorName)
				.append(this.creatorId).append(this.caseNums)
				.append(this.status).append(this.typeName)
				.append(this.openStyle).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("archFondId", this.archFondId)
				.append("afNo", this.afNo).append("afName", this.afName)
				.append("shortDesc", this.shortDesc)
				.append("descp", this.descp)
				.append("clearupDesc", this.clearupDesc)
				.append("createtime", this.createTime)
				.append("updatetime", this.updateTime)
				.append("creator", this.creatorName)
				.append("creatorId", this.creatorId)
				.append("caseNums", this.caseNums)
				.append("status", this.status)
				.append("typeName", this.typeName)
				.append("openStyle", this.openStyle).toString();
	}
}
