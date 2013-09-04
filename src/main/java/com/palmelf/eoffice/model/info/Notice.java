package com.palmelf.eoffice.model.info;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "notice")
public class Notice extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8751386382978074432L;
	private long noticeId;
	private String postName;
	private String noticeTitle;
	private String noticeContent;
	private Date effectiveDate;
	private Date expirationDate;
	private short state;

	@Id
	@GeneratedValue
	@Column(name = "noticeId", unique = true, nullable = false)
	public Long getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	@Column(name = "postName", nullable = false)
	public String getPostName() {
		return this.postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	@Column(name = "noticeTitle", nullable = false)
	public String getNoticeTitle() {
		return this.noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	@Column(name = "noticeContent", nullable = false)
	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	@Column(name = "effectiveDate", length = 19)
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name = "expirationDate", length = 19)
	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Column(name = "state")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}
}
