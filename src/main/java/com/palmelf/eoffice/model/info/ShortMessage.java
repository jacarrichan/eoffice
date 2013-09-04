package com.palmelf.eoffice.model.info;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "short_message")
public class ShortMessage extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8333050139790099303L;
	private Long messageId;
	private String content;
	private Long senderId;
	private String sender;
	private Short msgType;
	private Date sendTime;
	public static final Short MSG_TYPE_PERSONAL = 1;
	public static final Short MSG_TYPE_CALENDAR = 2;
	public static final Short MSG_TYPE_PLAN = 3;
	public static final Short MSG_TYPE_TASK = 4;
	public static final Short MSG_TYPE_SYS = 5;

	private Set<InMessage> messages = new HashSet<InMessage>();

	@Id
	@GeneratedValue
	@Column(name = "messageId", unique = true, nullable = false)
	public Long getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "sender", nullable = false)
	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Column(name = "msgType", nullable = false)
	public Short getMsgType() {
		return this.msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	@Column(name = "sendTime", nullable = false, length = 19)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "senderId", nullable = false)
	public Long getSenderId() {
		return this.senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shortMessage")
	public Set<InMessage> getMessages() {
		return this.messages;
	}

	public void setMessages(Set<InMessage> messages) {
		this.messages = messages;
	}

}
