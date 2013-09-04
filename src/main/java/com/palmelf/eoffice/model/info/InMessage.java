package com.palmelf.eoffice.model.info;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "in_message")
public class InMessage extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Short FLAG_READ = 1;
	public static final Short FLAG_UNREAD = 0;
	private Long receiveId;
	private ShortMessage shortMessage;
	private Long userId;
	private String userFullname;
	private Short readFlag;
	private Short delFlag;

	public InMessage() {
	}

	public InMessage(Long receiveId) {
		this.setReceiveId(receiveId);
	}

	@Id
	@GeneratedValue
	@Column(name = "receiveId", unique = true, nullable = false)
	public Long getReceiveId() {
		return this.receiveId;
	}

	public void setReceiveId(Long receiveId) {
		this.receiveId = receiveId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "messageId")
	public ShortMessage getShortMessage() {
		return this.shortMessage;
	}

	public void setShortMessage(ShortMessage shortMessage) {
		this.shortMessage = shortMessage;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "userFullname")
	public String getUserFullname() {
		return this.userFullname;
	}

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	@Column(name = "readFlag", nullable = false)
	public Short getReadFlag() {
		return this.readFlag;
	}

	public void setReadFlag(Short readFlag) {
		this.readFlag = readFlag;
	}

	@Column(name = "delFlag", nullable = false)
	public Short getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

}
