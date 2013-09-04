package com.palmelf.eoffice.model.info;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "suggest_box")
public class SuggestBox extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7456011780805386348L;

	public static final Short STATUS_DRAFT = 0;

	public static final Short STATUS_SEND = 1;

	public static final Short STATUS_AUDIT = 2;
	private Long boxId;
	private String subject;
	private String content;
	private Date createtime;
	private Long recUid;
	private String recFullname;
	private Long senderId;
	private String senderFullname;
	private String senderIp;
	private String phone;
	private String email;
	private Short isOpen;
	private String replyContent;
	private Date replyTime;
	private Long replyId;
	private String replyFullname;
	private Short status;
	private String queryPwd;

	public SuggestBox() {
	}

	public SuggestBox(Long in_boxId) {
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

	@Column(name = "subject", nullable = false, length = 256)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "content", nullable = false, length = 4000)
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

	@Column(name = "recUid")
	public Long getRecUid() {
		return this.recUid;
	}

	public void setRecUid(Long recUid) {
		this.recUid = recUid;
	}

	@Column(name = "recFullname", length = 32)
	public String getRecFullname() {
		return this.recFullname;
	}

	public void setRecFullname(String recFullname) {
		this.recFullname = recFullname;
	}

	@Column(name = "senderId")
	public Long getSenderId() {
		return this.senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	@Column(name = "senderFullname", length = 32)
	public String getSenderFullname() {
		return this.senderFullname;
	}

	public void setSenderFullname(String senderFullname) {
		this.senderFullname = senderFullname;
	}

	@Column(name = "senderIp", length = 64)
	public String getSenderIp() {
		return this.senderIp;
	}

	public void setSenderIp(String senderIp) {
		this.senderIp = senderIp;
	}

	@Column(name = "phone", length = 64)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "isOpen")
	public Short getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(Short isOpen) {
		this.isOpen = isOpen;
	}

	@Column(name = "replyContent", length = 4000)
	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Column(name = "replyTime", length = 19)
	public Date getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	@Column(name = "replyId")
	public Long getReplyId() {
		return this.replyId;
	}

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	@Column(name = "replyFullname", length = 32)
	public String getReplyFullname() {
		return this.replyFullname;
	}

	public void setReplyFullname(String replyFullname) {
		this.replyFullname = replyFullname;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "queryPwd", length = 128)
	public String getQueryPwd() {
		return this.queryPwd;
	}

	public void setQueryPwd(String queryPwd) {
		this.queryPwd = queryPwd;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SuggestBox)) {
			return false;
		}
		SuggestBox rhs = (SuggestBox) object;
		return new EqualsBuilder().append(this.boxId, rhs.boxId).append(this.subject, rhs.subject)
				.append(this.content, rhs.content).append(this.createtime, rhs.createtime)
				.append(this.recUid, rhs.recUid).append(this.recFullname, rhs.recFullname)
				.append(this.senderId, rhs.senderId).append(this.senderFullname, rhs.senderFullname)
				.append(this.senderIp, rhs.senderIp).append(this.phone, rhs.phone).append(this.email, rhs.email)
				.append(this.isOpen, rhs.isOpen).append(this.replyContent, rhs.replyContent)
				.append(this.replyTime, rhs.replyTime).append(this.replyId, rhs.replyId)
				.append(this.replyFullname, rhs.replyFullname).append(this.status, rhs.status)
				.append(this.queryPwd, rhs.queryPwd).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.boxId).append(this.subject).append(this.content)
				.append(this.createtime).append(this.recUid).append(this.recFullname).append(this.senderId)
				.append(this.senderFullname).append(this.senderIp).append(this.phone).append(this.email)
				.append(this.isOpen).append(this.replyContent).append(this.replyTime).append(this.replyId)
				.append(this.replyFullname).append(this.status).append(this.queryPwd).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("boxId", this.boxId).append("subject", this.subject)
				.append("content", this.content).append("createtime", this.createtime).append("recUid", this.recUid)
				.append("recFullname", this.recFullname).append("senderId", this.senderId)
				.append("senderFullname", this.senderFullname).append("senderIp", this.senderIp)
				.append("phone", this.phone).append("email", this.email).append("isOpen", this.isOpen)
				.append("replyContent", this.replyContent).append("replyTime", this.replyTime)
				.append("replyId", this.replyId).append("replyFullname", this.replyFullname)
				.append("status", this.status).append("queryPwd", this.queryPwd).toString();
	}
}
