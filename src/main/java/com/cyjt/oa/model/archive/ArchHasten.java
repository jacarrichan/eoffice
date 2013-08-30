package com.cyjt.oa.model.archive;

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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "arch_hasten")
public class ArchHasten extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6753888986366323714L;
	private Long recordId;
	private String content;
	private Date createtime;
	private String hastenFullname;
	private String handlerFullname;
	private Long handlerUserId;
	private Archives archives;

	public ArchHasten() {
	}

	public ArchHasten(Long in_recordId) {
		this.setRecordId(in_recordId);
	}

	@Id
	@GeneratedValue
	@Column(name = "recordId", unique = true, nullable = false)
	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "archivesId")
	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	@Column(name = "content", length = 1024)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "hastenFullname", length = 64)
	public String getHastenFullname() {
		return this.hastenFullname;
	}

	public void setHastenFullname(String hastenFullname) {
		this.hastenFullname = hastenFullname;
	}

	@Column(name = "handlerFullname", length = 64)
	public String getHandlerFullname() {
		return this.handlerFullname;
	}

	public void setHandlerFullname(String handlerFullname) {
		this.handlerFullname = handlerFullname;
	}

	@Column(name = "handlerUserId")
	public Long getHandlerUserId() {
		return this.handlerUserId;
	}

	public void setHandlerUserId(Long handlerUserId) {
		this.handlerUserId = handlerUserId;
	}
	@Transient
	public Long getArchivesId() {
		return this.getArchives() == null ? null : this.getArchives().getArchivesId();
	}

	public void setArchivesId(Long aValue) {
		if (aValue == null) {
			this.archives = null;
		} else if (this.archives == null) {
			this.archives = new Archives(aValue);
			this.archives.setVersion(new Integer(0));
		} else {
			this.archives.setArchivesId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ArchHasten)) {
			return false;
		}
		ArchHasten rhs = (ArchHasten) object;
		return new EqualsBuilder().append(this.recordId, rhs.recordId).append(this.content, rhs.content)
				.append(this.createtime, rhs.createtime).append(this.hastenFullname, rhs.hastenFullname)
				.append(this.handlerFullname, rhs.handlerFullname).append(this.handlerUserId, rhs.handlerUserId)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.recordId).append(this.content)
				.append(this.createtime).append(this.hastenFullname).append(this.handlerFullname)
				.append(this.handlerUserId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("recordId", this.recordId).append("content", this.content)
				.append("createtime", this.createtime).append("hastenFullname", this.hastenFullname)
				.append("handlerFullname", this.handlerFullname).append("handlerUserId", this.handlerUserId).toString();
	}
}
