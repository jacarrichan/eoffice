package com.cyjt.oa.model.communicate;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.FileAttach;

@Entity
@Table(name = "mail")
public class Mail extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long mailId;
	private String sender;
	private Short importantFlag;
	private Date sendTime;
	private String content;
	private String subject;
	private String copyToNames;
	private String copyToIDs;
	private String recipientNames;
	private String recipientIDs;
	private Short mailStatus;
	private AppUser appSender;
	private String fileIds;
	private String filenames;
	private Set<FileAttach> mailAttachs = new HashSet<FileAttach>();
	private Set<MailBox> mailBoxs = new HashSet<MailBox>();

	public Mail() {
	}

	public Mail(Long in_mailId) {
		this.setMailId(in_mailId);
	}

	@Id
	@GeneratedValue
	@Column(name = "mailId", unique = true, nullable = false)
	public Long getMailId() {
		return this.mailId;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "senderId")
	public AppUser getAppSender() {
		return this.appSender;
	}

	public void setAppSender(AppUser appUser) {
		this.appSender = appUser;
	}

	@Column(name = "sender", nullable = false, length = 32)
	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Column(name = "importantFlag", nullable = false)
	public Short getImportantFlag() {
		return this.importantFlag;
	}

	public void setImportantFlag(Short importantFlag) {
		this.importantFlag = importantFlag;
	}

	@Column(name = "sendTime", nullable = false, length = 19)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "content", nullable = false, length = 5000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "subject", nullable = false, length = 256)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "copyToNames", length = 1000)
	public String getCopyToNames() {
		return this.copyToNames;
	}

	public void setCopyToNames(String copyToNames) {
		this.copyToNames = copyToNames;
	}

	@Column(name = "copyToIDs", length = 1000)
	public String getCopyToIDs() {
		return this.copyToIDs;
	}

	public void setCopyToIDs(String copyToIds) {
		this.copyToIDs = copyToIds;
	}

	@Column(name = "recipientNames", length = 1000)
	public String getRecipientNames() {
		return this.recipientNames;
	}

	public void setRecipientNames(String recipientNames) {
		this.recipientNames = recipientNames;
	}

	@Column(name = "recipientIDs", length = 1000)
	public String getRecipientIDs() {
		return this.recipientIDs;
	}

	public void setRecipientIDs(String recipientIds) {
		this.recipientIDs = recipientIds;
	}

	@Column(name = "mailStatus", nullable = false)
	public Short getMailStatus() {
		return this.mailStatus;
	}

	public void setMailStatus(Short mailStatus) {
		this.mailStatus = mailStatus;
	}

	@Column(name = "fileIds", length = 500)
	public String getFileIds() {
		return this.fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	@Column(name = "filenames", length = 500)
	public String getFilenames() {
		return this.filenames;
	}

	public void setFilenames(String filenames) {
		this.filenames = filenames;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "mail_attach", joinColumns = { @JoinColumn(name = "mailId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "fileId", nullable = false, updatable = false) })
	public Set<FileAttach> getMailAttachs() {
		return this.mailAttachs;
	}

	public void setMailAttachs(Set<FileAttach> fileAttachs) {
		this.mailAttachs = fileAttachs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mail")
	public Set<MailBox> getMailBoxs() {
		return this.mailBoxs;
	}

	public void setMailBoxs(Set<MailBox> mailBoxes) {
		this.mailBoxs = mailBoxes;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Mail)) {
			return false;
		}
		Mail rhs = (Mail) object;
		return new EqualsBuilder().append(this.mailId, rhs.mailId).append(this.sender, rhs.sender)
				.append(this.importantFlag, rhs.importantFlag).append(this.sendTime, rhs.sendTime)
				.append(this.content, rhs.content).append(this.subject, rhs.subject)
				.append(this.copyToNames, rhs.copyToNames).append(this.copyToIDs, rhs.copyToIDs)
				.append(this.recipientNames, rhs.recipientNames).append(this.recipientIDs, rhs.recipientIDs)
				.append(this.mailStatus, rhs.mailStatus).append(this.fileIds, rhs.fileIds)
				.append(this.filenames, rhs.filenames).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.mailId).append(this.sender)
				.append(this.importantFlag).append(this.sendTime).append(this.content).append(this.subject)
				.append(this.copyToNames).append(this.copyToIDs).append(this.recipientNames).append(this.recipientIDs)
				.append(this.mailStatus).append(this.fileIds).append(this.filenames).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("mailId", this.mailId).append("sender", this.sender)
				.append("importantFlag", this.importantFlag).append("sendTime", this.sendTime)
				.append("content", this.content).append("subject", this.subject)
				.append("copyToNames", this.copyToNames).append("copyToIDs", this.copyToIDs)
				.append("recipientNames", this.recipientNames).append("recipientIDs", this.recipientIDs)
				.append("mailStatus", this.mailStatus).append("fileIds", this.fileIds)
				.append("filenames", this.filenames).toString();
	}

}
