package com.palmelf.eoffice.model.admin;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.FileAttach;
import com.palmelf.eoffice.model.system.GlobalType;

@Entity
@Table(name = "regulation")
public class Regulation extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -549674143583733822L;

	public static final Short STATUS_DRAFT = 0;

	public static final Short STATUS_EFFECT = 1;
	private Long regId;
	private String subject;
	private Date issueDate;
	private Long issueUserId;
	private String issueFullname;
	private Long issueDepId;
	private String issueDep;
	private String recDeps;
	private String recDepIds;
	private String recUsers;
	private String recUserIds;
	private String content;
	private String keywords;
	private Short status;
	private GlobalType globalType;
	private Set<FileAttach> regAttachs = new HashSet<FileAttach>();

	public Regulation() {
	}

	public Regulation(Long in_regId) {
		this.setRegId(in_regId);
	}
	@Transient
	public Long getProTypeId() {
		return this.getGlobalType() == null ? null : this.getGlobalType().getProTypeId();
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

	@Id
	@GeneratedValue
	@Column(name = "regId", unique = true, nullable = false)
	public Long getRegId() {
		return this.regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proTypeId")
	public GlobalType getGlobalType() {
		return this.globalType;
	}

	public void setGlobalType(GlobalType globalType) {
		this.globalType = globalType;
	}

	@Column(name = "subject", nullable = false, length = 256)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "issueDate", length = 19)
	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@Column(name = "issueUserId")
	public Long getIssueUserId() {
		return this.issueUserId;
	}

	public void setIssueUserId(Long issueUserId) {
		this.issueUserId = issueUserId;
	}

	@Column(name = "issueFullname", length = 64)
	public String getIssueFullname() {
		return this.issueFullname;
	}

	public void setIssueFullname(String issueFullname) {
		this.issueFullname = issueFullname;
	}

	@Column(name = "issueDepId")
	public Long getIssueDepId() {
		return this.issueDepId;
	}

	public void setIssueDepId(Long issueDepId) {
		this.issueDepId = issueDepId;
	}

	@Column(name = "issueDep", length = 64)
	public String getIssueDep() {
		return this.issueDep;
	}

	public void setIssueDep(String issueDep) {
		this.issueDep = issueDep;
	}

	@Column(name = "recDeps", length = 1024)
	public String getRecDeps() {
		return this.recDeps;
	}

	public void setRecDeps(String recDeps) {
		this.recDeps = recDeps;
	}

	@Column(name = "recDepIds", length = 1024)
	public String getRecDepIds() {
		return this.recDepIds;
	}

	public void setRecDepIds(String recDepIds) {
		this.recDepIds = recDepIds;
	}

	@Column(name = "recUsers", length = 1024)
	public String getRecUsers() {
		return this.recUsers;
	}

	public void setRecUsers(String recUsers) {
		this.recUsers = recUsers;
	}

	@Column(name = "recUserIds", length = 1024)
	public String getRecUserIds() {
		return this.recUserIds;
	}

	public void setRecUserIds(String recUserIds) {
		this.recUserIds = recUserIds;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "keywords", length = 256)
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "reg_attach", joinColumns = { @JoinColumn(name = "regId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "fileId", nullable = false, updatable = false) })
	public Set<FileAttach> getRegAttachs() {
		return this.regAttachs;
	}

	public void setRegAttachs(Set<FileAttach> fileAttachs) {
		this.regAttachs = fileAttachs;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Regulation)) {
			return false;
		}
		Regulation rhs = (Regulation) object;
		return new EqualsBuilder().append(this.regId, rhs.regId).append(this.subject, rhs.subject)
				.append(this.issueDate, rhs.issueDate).append(this.issueUserId, rhs.issueUserId)
				.append(this.issueFullname, rhs.issueFullname).append(this.issueDepId, rhs.issueDepId)
				.append(this.issueDep, rhs.issueDep).append(this.recDeps, rhs.recDeps)
				.append(this.recDepIds, rhs.recDepIds).append(this.recUsers, rhs.recUsers)
				.append(this.recUserIds, rhs.recUserIds).append(this.content, rhs.content)
				.append(this.keywords, rhs.keywords).append(this.status, rhs.status).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.regId).append(this.subject)
				.append(this.issueDate).append(this.issueUserId).append(this.issueFullname).append(this.issueDepId)
				.append(this.issueDep).append(this.recDeps).append(this.recDepIds).append(this.recUsers)
				.append(this.recUserIds).append(this.content).append(this.keywords).append(this.status).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("regId", this.regId).append("subject", this.subject)
				.append("issueDate", this.issueDate).append("issueUserId", this.issueUserId)
				.append("issueFullname", this.issueFullname).append("issueDepId", this.issueDepId)
				.append("issueDep", this.issueDep).append("recDeps", this.recDeps).append("recDepIds", this.recDepIds)
				.append("recUsers", this.recUsers).append("recUserIds", this.recUserIds)
				.append("content", this.content).append("keywords", this.keywords).append("status", this.status)
				.toString();
	}
}
