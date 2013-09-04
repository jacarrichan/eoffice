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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.FileAttach;

@Entity
@Table(name = "conf_summary")
public class ConfSummary extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4802924035069500594L;
	private Conference confId;
	private Long sumId;
	private Date createtime;
	private String creator;
	private String sumContent;
	private Short status;
	private Set<FileAttach> attachFiles = new HashSet<FileAttach>();

	@Id
	@GeneratedValue
	@Column(name = "sumId", unique = true, nullable = false)
	public Long getSumId() {
		return this.sumId;
	}

	public void setSumId(Long sumId) {
		this.sumId = sumId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "confId")
	public Conference getConfId() {
		return this.confId;
	}

	public void setConfId(Conference conference) {
		this.confId = conference;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "creator", nullable = false, length = 32)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "sumContent", nullable = false)
	public String getSumContent() {
		return this.sumContent;
	}

	public void setSumContent(String sumContent) {
		this.sumContent = sumContent;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "conf_sum_attach", joinColumns = { @JoinColumn(name = "sumId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "fileId", nullable = false, updatable = false) })
	public Set<FileAttach> getAttachFiles() {
		return this.attachFiles;
	}

	public void setAttachFiles(Set<FileAttach> fileAttachs) {
		this.attachFiles = fileAttachs;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ConfSummary)) {
			return false;
		}
		ConfSummary rhs = (ConfSummary) object;
		return new EqualsBuilder().append(this.sumId, rhs.sumId).append(this.confId, rhs.confId)
				.append(this.createtime, rhs.createtime).append(this.creator, rhs.creator)
				.append(this.sumContent, rhs.sumContent).append(this.status, rhs.status).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.sumId).append(this.confId)
				.append(this.createtime).append(this.creator).append(this.sumContent).append(this.status).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("sumId", this.sumId).append("confId", this.confId)
				.append("createtime", this.createtime).append("creator", this.creator)
				.append("sumContent", this.sumContent).append("status", this.status).toString();
	}
}
