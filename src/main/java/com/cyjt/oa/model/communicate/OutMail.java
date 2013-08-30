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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.FileAttach;

@Entity
@Table(name = "out_mail")
public class OutMail extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2842904985761411487L;
	private Long mailId;
	private String uid;
	private Long userId;
	private String title;
	private String content;
	private String senderAddresses;
	private String senderName;
	private String receiverAddresses;
	private String receiverNames;
	private String cCAddresses;
	private String cCNames;
	private String bCCAddresses;
	private String bCCAnames;
	private Date mailDate;
	private String fileIds;
	private String fileNames;
	private Short readFlag;
	private Short replyFlag;
	private OutMailFolder outMailFolder;
	private Set<FileAttach> outMailFiles = new HashSet<FileAttach>();

	public OutMail() {
	}

	public OutMail(Long in_mailId) {
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
	@JoinColumn(name = "folderId")
	public OutMailFolder getOutMailFolder() {
		return this.outMailFolder;
	}

	public void setOutMailFolder(OutMailFolder outMailFolder) {
		this.outMailFolder = outMailFolder;
	}

	@Column(name = "title", length = 512)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "senderAddresses", nullable = false, length = 128)
	public String getSenderAddresses() {
		return this.senderAddresses;
	}

	public void setSenderAddresses(String senderAddresses) {
		this.senderAddresses = senderAddresses;
	}

	@Column(name = "senderName", length = 128)
	public String getSenderName() {
		return this.senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	@Column(name = "receiverAddresses", nullable = false)
	public String getReceiverAddresses() {
		return this.receiverAddresses;
	}

	public void setReceiverAddresses(String receiverAddresses) {
		this.receiverAddresses = receiverAddresses;
	}

	@Column(name = "receiverNames")
	public String getReceiverNames() {
		return this.receiverNames;
	}

	public void setReceiverNames(String receiverNames) {
		this.receiverNames = receiverNames;
	}

	@Column(name = "mailDate", nullable = false, length = 19)
	public Date getMailDate() {
		return this.mailDate;
	}

	public void setMailDate(Date mailDate) {
		this.mailDate = mailDate;
	}

	@Column(name = "fileIds", length = 128)
	public String getFileIds() {
		return this.fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	@Column(name = "fileNames", length = 128)
	public String getFileNames() {
		return this.fileNames;
	}

	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}

	@Column(name = "readFlag", nullable = false)
	public Short getReadFlag() {
		return this.readFlag;
	}

	public void setReadFlag(Short readFlag) {
		this.readFlag = readFlag;
	}

	@Column(name = "replyFlag", nullable = false)
	public Short getReplyFlag() {
		return this.replyFlag;
	}

	public void setReplyFlag(Short replyFlag) {
		this.replyFlag = replyFlag;
	}
	@Transient
	public Long getFolderId() {
		return this.getOutMailFolder() == null ? null : this.getOutMailFolder().getFolderId();
	}

	public void setFolderId(Long aValue) {
		if (aValue == null) {
			this.outMailFolder = null;
		} else if (this.outMailFolder == null) {
			this.outMailFolder = new OutMailFolder(aValue);
			this.outMailFolder.setVersion(new Integer(0));
		} else {
			this.outMailFolder.setFolderId(aValue);
		}
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "cCAddresses")
	public String getcCAddresses() {
		return this.cCAddresses;
	}

	public void setcCAddresses(String cCAddresses) {
		this.cCAddresses = cCAddresses;
	}

	@Column(name = "cCNames")
	public String getcCNames() {
		return this.cCNames;
	}

	public void setcCNames(String cCNames) {
		this.cCNames = cCNames;
	}

	@Column(name = "bCCAddresses")
	public String getbCCAddresses() {
		return this.bCCAddresses;
	}

	public void setbCCAddresses(String bCCAddresses) {
		this.bCCAddresses = bCCAddresses;
	}

	@Column(name = "bCCAnames")
	public String getbCCAnames() {
		return this.bCCAnames;
	}

	public void setbCCAnames(String bCCAnames) {
		this.bCCAnames = bCCAnames;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "out_mail_file", joinColumns = { @JoinColumn(name = "mailId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "fileId", nullable = false, updatable = false) })
	public Set<FileAttach> getOutMailFiles() {
		return this.outMailFiles;
	}

	public void setOutMailFiles(Set<FileAttach> outMailFiles) {
		this.outMailFiles = outMailFiles;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof OutMail)) {
			return false;
		}
		OutMail rhs = (OutMail) object;
		return new EqualsBuilder().append(this.mailId, rhs.mailId).append(this.title, rhs.title)
				.append(this.content, rhs.content).append(this.senderAddresses, rhs.senderAddresses)
				.append(this.senderName, rhs.senderName).append(this.receiverAddresses, rhs.receiverAddresses)
				.append(this.receiverNames, rhs.receiverNames).append(this.cCAddresses, rhs.cCAddresses)
				.append(this.cCNames, rhs.cCNames).append(this.bCCAddresses, rhs.bCCAddresses)
				.append(this.bCCAnames, rhs.bCCAnames).append(this.mailDate, rhs.mailDate)
				.append(this.fileIds, rhs.fileIds).append(this.fileNames, rhs.fileNames)
				.append(this.readFlag, rhs.readFlag).append(this.replyFlag, rhs.replyFlag).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.mailId).append(this.title).append(this.content)
				.append(this.senderAddresses).append(this.senderName).append(this.receiverAddresses)
				.append(this.receiverNames).append(this.cCAddresses).append(this.cCNames).append(this.bCCAddresses)
				.append(this.bCCAnames).append(this.mailDate).append(this.fileIds).append(this.fileNames)
				.append(this.readFlag).append(this.replyFlag).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("mailId", this.mailId).append("title", this.title)
				.append("content", this.content).append("senderAddresses", this.senderAddresses)
				.append("senderName", this.senderName).append("receiverAddresses", this.receiverAddresses)
				.append("receiverNames", this.receiverNames).append("cCAddresses", this.cCAddresses)
				.append("cCNames", this.cCNames).append("bCCAddresses", this.bCCAddresses)
				.append("bCCAnames", this.bCCAnames).append("mailDate", this.mailDate).append("fileIds", this.fileIds)
				.append("fileNames", this.fileNames).append("readFlag", this.readFlag)
				.append("replyFlag", this.replyFlag).toString();
	}
}
