package com.palmelf.eoffice.model.communicate;

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
@Table(name = "sms_history")
public class SmsHistory extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7013447935052816884L;
	protected Long smsId;
	protected Date sendTime;
	protected String recipients;
	protected String phoneNumber;
	protected Long userId;
	protected String userName;
	protected String smsContent;
	protected Short status;

	public SmsHistory() {
	}

	public SmsHistory(Long in_smsId) {
		this.setSmsId(in_smsId);
	}

	@Id
	@GeneratedValue
	@Column(name = "smsId")
	public Long getSmsId() {
		return this.smsId;
	}

	public void setSmsId(Long aValue) {
		this.smsId = aValue;
	}

	@Column(name = "sendTime", nullable = false, length = 10)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date aValue) {
		this.sendTime = aValue;
	}

	@Column(name = "recipients", length = 128)
	public String getRecipients() {
		return this.recipients;
	}

	public void setRecipients(String aValue) {
		this.recipients = aValue;
	}

	@Column(name = "phoneNumber", length = 128)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String aValue) {
		this.phoneNumber = aValue;
	}

	@Column(name = "userId", length = 19)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long aValue) {
		this.userId = aValue;
	}

	@Column(name = "userName", length = 128)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String aValue) {
		this.userName = aValue;
	}

	@Column(name = "smsContent", length = 1024)
	public String getSmsContent() {
		return this.smsContent;
	}

	public void setSmsContent(String aValue) {
		this.smsContent = aValue;
	}

	@Column(name = "status", length = 5)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short aValue) {
		this.status = aValue;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SmsHistory)) {
			return false;
		}
		SmsHistory rhs = (SmsHistory) object;
		return new EqualsBuilder().append(this.smsId, rhs.smsId).append(this.sendTime, rhs.sendTime)
				.append(this.recipients, rhs.recipients).append(this.phoneNumber, rhs.phoneNumber)
				.append(this.userId, rhs.userId).append(this.userName, rhs.userName)
				.append(this.smsContent, rhs.smsContent).append(this.status, rhs.status).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.smsId).append(this.sendTime)
				.append(this.recipients).append(this.phoneNumber).append(this.userId).append(this.userName)
				.append(this.smsContent).append(this.status).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("smsId", this.smsId).append("sendTime", this.sendTime)
				.append("recipients", this.recipients).append("phoneNumber", this.phoneNumber)
				.append("userId", this.userId).append("userName", this.userName).append("smsContent", this.smsContent)
				.append("status", this.status).toString();
	}
}