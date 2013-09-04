package com.palmelf.eoffice.model.archive;

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
import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.Department;

@Entity
@Table(name = "archives")
public class Archives extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3983821218073146466L;

	public static final Short STATUS_DRAFT = 0;

	public static final Short STATUS_ISSUE = 1;

	public static final Short STATUS_ARCHIVE = 2;

	public static final Short STATUS_SUBMITCHECK = 1;

	public static final Short STATUS_LEADERCHECK = 2;

	public static final Short STATUS_HANDLING = 5;

	public static final Short STATUS_APPLYBACK = 6;

	public static final Short STATUS_BACK = 7;

	public static final Short STATUS_HANDLE = 1;

	public static final Short STATUS_HANDLEING = 2;

	public static final Short STATUS_LEADERREAD = 3;

	public static final Short STATUS_DISPATCH = 4;

	public static final Short STATUS_READ = 5;

	public static final Short STATUS_READING = 6;

	public static final Short STATUS_END = 7;

	public static final Short ARCHIVE_TYPE_DISPATCH = 0;

	public static final Short ARCHIVE_TYPE_RECEIVE = 1;

	@Expose
	protected Long archivesId;

	@Expose
	protected String typeName;

	@Expose
	protected String archivesNo;

	@Expose
	protected String issueDep;

	@Expose
	protected String subject;

	@Expose
	protected Date issueDate;

	@Expose
	protected Date createtime;

	@Expose
	protected Short status;

	@Expose
	protected String shortContent;

	@Expose
	protected Integer fileCounts;

	@Expose
	protected String privacyLevel;

	@Expose
	protected String urgentLevel;

	@Expose
	protected String issuer;

	@Expose
	protected Long issuerId;

	@Expose
	protected String keywords;

	@Expose
	protected String sources;

	@Expose
	protected Short archType;

	@Expose
	protected String recDepIds;

	@Expose
	protected String recDepNames;

	@Expose
	protected String handlerUids;

	@Expose
	protected String handlerUnames;

	@Expose
	protected Long orgArchivesId;

	@Expose
	protected String depSignNo;

	@Expose
	protected ArchivesType archivesType;

	@Expose
	protected Department department;

	@Expose
	protected ArchRecType archRecType;

	@Expose
	protected Set<ArchivesHandle> archivesHandles = new HashSet<ArchivesHandle>();

	@Expose
	protected Set<ArchivesDep> archivesDeps = new HashSet<ArchivesDep>();

	@Expose
	protected Set<ArchivesDoc> archivesDocs = new HashSet<ArchivesDoc>();
	protected Set<LeaderRead> leaders = new HashSet<LeaderRead>();
	protected Set<ArchDispatch> archivesDispatch = new HashSet<ArchDispatch>();
	protected Set<ArchivesAttend> archivesAttends = new HashSet<ArchivesAttend>();
	
	private String handleOpinion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "archives")
	public Set<ArchivesAttend> getArchivesAttends() {
		return this.archivesAttends;
	}

	public void setArchivesAttends(Set<ArchivesAttend> archivesAttends) {
		this.archivesAttends = archivesAttends;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "archives")
	public Set<ArchDispatch> getArchivesDispatch() {
		return this.archivesDispatch;
	}

	public void setArchivesDispatch(Set<ArchDispatch> archivesDispatch) {
		this.archivesDispatch = archivesDispatch;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "archives")
	public Set<LeaderRead> getLeaders() {
		return this.leaders;
	}

	public void setLeaders(Set<LeaderRead> leaders) {
		this.leaders = leaders;
	}

	@Column(name = "handlerUids", length = 256)
	public String getHandlerUids() {
		return this.handlerUids;
	}

	public void setHandlerUids(String handlerUids) {
		this.handlerUids = handlerUids;
	}

	@Column(name = "handlerUnames", length = 256)
	public String getHandlerUnames() {
		return this.handlerUnames;
	}

	public void setHandlerUnames(String handlerUnames) {
		this.handlerUnames = handlerUnames;
	}

	public Archives() {
	}

	public Archives(Long in_archivesId) {
		this.setArchivesId(in_archivesId);
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "archives")
	public Set<ArchivesHandle> getArchivesHandles() {
		return this.archivesHandles;
	}

	public void setArchivesHandles(Set<ArchivesHandle> archivesHandles) {
		this.archivesHandles = archivesHandles;
	}

	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "arc_typeId")
	public ArchRecType getArchRecType() {
		return this.archRecType;
	}

	public void setArchRecType(ArchRecType archRecType) {
		this.archRecType = archRecType;
	}

	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "typeId")
	public ArchivesType getArchivesType() {
		return this.archivesType;
	}

	public void setArchivesType(ArchivesType in_archivesType) {
		this.archivesType = in_archivesType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depId")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department in_department) {
		this.department = in_department;
	}

	@OneToMany(mappedBy = "archives")
	public Set<ArchivesDep> getArchivesDeps() {
		return this.archivesDeps;
	}

	public void setArchivesDeps(Set<ArchivesDep> in_archivesDeps) {
		this.archivesDeps = in_archivesDeps;
	}

	@Id
	@GeneratedValue
	@Column(name = "archivesId", unique = true, nullable = false)
	public Long getArchivesId() {
		return this.archivesId;
	}

	public void setArchivesId(Long aValue) {
		this.archivesId = aValue;
	}

	@Transient
	public Long getTypeId() {
		return this.getArchivesType() == null ? null : this.getArchivesType()
				.getTypeId();
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
	public Long getRecTypeId() {
		return this.getArchRecType() == null ? null : this.getArchRecType()
				.getRecTypeId();
	}

	public void setRecTypeId(Long aValue) {
		if (aValue == null) {
			this.archRecType = null;
		} else if (this.archRecType == null) {
			this.archRecType = new ArchRecType(aValue);
			this.archRecType.setVersion(new Integer(0));
		} else {
			this.archRecType.setRecTypeId(aValue);
		}
	}

	@Column(name = "typeName", length = 128)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String aValue) {
		this.typeName = aValue;
	}

	@Column(name = "archivesNo", length = 100)
	public String getArchivesNo() {
		return this.archivesNo;
	}

	public void setArchivesNo(String aValue) {
		this.archivesNo = aValue;
	}

	@Column(name = "issueDep", nullable = false, length = 128)
	public String getIssueDep() {
		return this.issueDep;
	}

	public void setIssueDep(String aValue) {
		this.issueDep = aValue;
	}

	@Transient
	public Long getDepId() {
		return this.getDepartment() == null ? null : this.getDepartment()
				.getDepId();
	}

	public void setDepId(Long aValue) {
		if (aValue == null) {
			this.department = null;
		} else if (this.department == null) {
			this.department = new Department(aValue);
			this.department.setVersion(new Integer(0));
		} else {
			this.department.setDepId(aValue);
		}
	}

	@Column(name = "subject", nullable = false, length = 256)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String aValue) {
		this.subject = aValue;
	}

	@Column(name = "issueDate", nullable = false, length = 19)
	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date aValue) {
		this.issueDate = aValue;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short aValue) {
		this.status = aValue;
	}

	@Column(name = "shortContent", length = 1024)
	public String getShortContent() {
		return this.shortContent;
	}

	public void setShortContent(String aValue) {
		this.shortContent = aValue;
	}

	@Column(name = "fileCounts", nullable = false)
	public Integer getFileCounts() {
		return this.fileCounts;
	}

	public void setFileCounts(Integer aValue) {
		this.fileCounts = aValue;
	}

	@Column(name = "privacyLevel", length = 50)
	public String getPrivacyLevel() {
		return this.privacyLevel;
	}

	public void setPrivacyLevel(String aValue) {
		this.privacyLevel = aValue;
	}

	@Column(name = "urgentLevel", length = 50)
	public String getUrgentLevel() {
		return this.urgentLevel;
	}

	public void setUrgentLevel(String aValue) {
		this.urgentLevel = aValue;
	}

	@Column(name = "issuer", length = 50)
	public String getIssuer() {
		return this.issuer;
	}

	public void setIssuer(String aValue) {
		this.issuer = aValue;
	}

	@Column(name = "issuerId")
	public Long getIssuerId() {
		return this.issuerId;
	}

	public void setIssuerId(Long aValue) {
		this.issuerId = aValue;
	}

	@Column(name = "keywords", length = 256)
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String aValue) {
		this.keywords = aValue;
	}

	@Column(name = "sources", length = 50)
	public String getSources() {
		return this.sources;
	}

	public void setSources(String aValue) {
		this.sources = aValue;
	}

	@Column(name = "archType", nullable = false)
	public Short getArchType() {
		return this.archType;
	}

	public void setArchType(Short aValue) {
		this.archType = aValue;
	}

	@Column(name = "recDepIds", length = 2000)
	public String getRecDepIds() {
		return this.recDepIds;
	}

	public void setRecDepIds(String recDepIds) {
		this.recDepIds = recDepIds;
	}

	@Column(name = "recDepNames", length = 2000)
	public String getRecDepNames() {
		return this.recDepNames;
	}

	public void setRecDepNames(String recDepNames) {
		this.recDepNames = recDepNames;
	}

	@Column(name = "orgArchivesId")
	public Long getOrgArchivesId() {
		return this.orgArchivesId;
	}

	public void setOrgArchivesId(Long orgArchivesId) {
		this.orgArchivesId = orgArchivesId;
	}

	@Column(name = "depSignNo", length = 100)
	public String getDepSignNo() {
		return this.depSignNo;
	}

	public void setDepSignNo(String depSignNo) {
		this.depSignNo = depSignNo;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Archives)) {
			return false;
		}
		Archives rhs = (Archives) object;
		return new EqualsBuilder().append(this.archivesId, rhs.archivesId)
				.append(this.typeName, rhs.typeName)
				.append(this.archivesNo, rhs.archivesNo)
				.append(this.issueDep, rhs.issueDep)
				.append(this.subject, rhs.subject)
				.append(this.issueDate, rhs.issueDate)
				.append(this.status, rhs.status)
				.append(this.shortContent, rhs.shortContent)
				.append(this.fileCounts, rhs.fileCounts)
				.append(this.privacyLevel, rhs.privacyLevel)
				.append(this.urgentLevel, rhs.urgentLevel)
				.append(this.issuer, rhs.issuer)
				.append(this.issuerId, rhs.issuerId)
				.append(this.keywords, rhs.keywords)
				.append(this.sources, rhs.sources)
				.append(this.archType, rhs.archType)
				.append(this.recDepIds, rhs.recDepIds)
				.append(this.recDepNames, rhs.recDepNames)
				.append(this.orgArchivesId, rhs.orgArchivesId)
				.append(this.depSignNo, rhs.depSignNo).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.archivesId).append(this.typeName)
				.append(this.archivesNo).append(this.issueDep)
				.append(this.subject).append(this.issueDate)
				.append(this.status).append(this.shortContent)
				.append(this.fileCounts).append(this.privacyLevel)
				.append(this.urgentLevel).append(this.issuer)
				.append(this.issuerId).append(this.keywords)
				.append(this.sources).append(this.archType)
				.append(this.recDepIds).append(this.recDepNames)
				.append(this.orgArchivesId).append(this.depSignNo).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("archivesId", this.archivesId)
				.append("typeName", this.typeName)
				.append("archivesNo", this.archivesNo)
				.append("issueDep", this.issueDep)
				.append("subject", this.subject)
				.append("issueDate", this.issueDate)
				.append("status", this.status)
				.append("shortContent", this.shortContent)
				.append("fileCounts", this.fileCounts)
				.append("privacyLevel", this.privacyLevel)
				.append("urgentLevel", this.urgentLevel)
				.append("issuer", this.issuer)
				.append("issuerId", this.issuerId)
				.append("keywords", this.keywords)
				.append("sources", this.sources)
				.append("archType", this.archType)
				.append("recDepIds", this.recDepIds)
				.append("recDepNames", this.recDepNames)
				.append("orgArchivesId", this.orgArchivesId)
				.append("depSignNo", this.depSignNo).toString();
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "archives")
	public Set<ArchivesDoc> getArchivesDocs() {
		return this.archivesDocs;
	}

	public void setArchivesDocs(Set<ArchivesDoc> archivesDocs) {
		this.archivesDocs = archivesDocs;
	}

	public void setHandleOpinion(String handleOpinion) {
	    this.handleOpinion = handleOpinion;
    }
	@Transient
	public String getHandleOpinion() {
	    return handleOpinion;
    }
}
