package com.cyjt.oa.model.communicate;

import java.util.Date;

import javax.persistence.CascadeType;
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
import com.cyjt.oa.model.system.AppUser;

@Entity
@Table(name = "mail_box")
public class MailBox extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4207254606504505303L;
	private Long boxId;
	private Date sendTime;
	private Short delFlag;
	private Short readFlag;
	private String note;
	private Mail mail;
	private AppUser appUser;
	private MailFolder mailFolder;
	private Short replyFlag;

	public MailBox() {
	}

	public MailBox(Long in_boxId) {
		this.setBoxId(in_boxId);
	}

	@Id
	@GeneratedValue
	@Column(name = "boxId", unique = true, nullable = false)
	public Long getBoxId() {
		return this.boxId;
	}

	public void setBoxId(Long boxId) {
		this.boxId = boxId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mailId")
	public Mail getMail() {
		return this.mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "folderId")
	public MailFolder getMailFolder() {
		return this.mailFolder;
	}

	public void setMailFolder(MailFolder mailFolder) {
		this.mailFolder = mailFolder;
	}

	@Column(name = "sendTime", nullable = false, length = 19)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "delFlag", nullable = false)
	public Short getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "readFlag", nullable = false)
	public Short getReadFlag() {
		return this.readFlag;
	}

	public void setReadFlag(Short readFlag) {
		this.readFlag = readFlag;
	}

	@Column(name = "note", length = 256)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "replyFlag", nullable = false)
	public Short getReplyFlag() {
		return this.replyFlag;
	}

	public void setReplyFlag(Short replyFlag) {
		this.replyFlag = replyFlag;
	}
	@Transient
	public Long getMailId() {
		return this.getMail() == null ? null : this.getMail().getMailId();
	}

	public void setMailId(Long aValue) {
		if (aValue == null) {
			this.mail = null;
		} else if (this.mail == null) {
			this.mail = new Mail(aValue);
			this.mail.setVersion(new Integer(0));
		} else {
			this.mail.setMailId(aValue);
		}
	}
	@Transient
	public Long getFolderId() {
		return this.getMailFolder() == null ? null : this.getMailFolder()
				.getFolderId();
	}

	public void setFolderId(Long aValue) {
		if (aValue == null) {
			this.mailFolder = null;
		} else if (this.mailFolder == null) {
			this.mailFolder = new MailFolder(aValue);
			this.mailFolder.setVersion(new Integer(0));
		} else {
			this.mailFolder.setFolderId(aValue);
		}
	}
	@Transient
	public Long getUserId() {
		return this.getAppUser() == null ? null : this.getAppUser().getUserId();
	}

	public void setUserId(Long aValue) {
		if (aValue == null) {
			this.appUser = null;
		} else if (this.appUser == null) {
			this.appUser = new AppUser(aValue);
			this.appUser.setVersion(new Integer(0));
		} else {
			this.appUser.setUserId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MailBox)) {
			return false;
		}
		MailBox rhs = (MailBox) object;
		return new EqualsBuilder().append(this.boxId, rhs.boxId)
				.append(this.sendTime, rhs.sendTime)
				.append(this.delFlag, rhs.delFlag)
				.append(this.readFlag, rhs.readFlag)
				.append(this.note, rhs.note)
				.append(this.replyFlag, this.replyFlag).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.boxId)
				.append(this.sendTime).append(this.delFlag)
				.append(this.readFlag).append(this.note).append(this.replyFlag)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("boxId", this.boxId)
				.append("sendTime", this.sendTime)
				.append("delFlag", this.delFlag)
				.append("readFlag", this.readFlag).append("note", this.note)
				.append("replyFlag", this.replyFlag).toString();
	}

}
