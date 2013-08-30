package com.cyjt.oa.model.communicate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "mobile_msg")
public class MobileMsg extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7766982313794507291L;
	public static final Short STATUS_INIT = 0;
	public static final Short STATUS_SUCESS = 1;
	public static final Short STATUS_FAIL = 2;
	private Long msgId;
	private String content;
	private String mobileNo;
	private Date createtime;
	private Short status;

	public MobileMsg() {
	}

	public MobileMsg(Long in_msgId) {
		this.setMsgId(in_msgId);
	}

	@Id
	@GeneratedValue
	@Column(name = "msgId", unique = true, nullable = false)
	public Long getMsgId() {
		return this.msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	@Column(name = "content", nullable = false, length = 512)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "mobileNo", nullable = false, length = 64)
	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MobileMsg)) {
			return false;
		}
		MobileMsg rhs = (MobileMsg) object;
		return new EqualsBuilder().append(this.msgId, rhs.msgId).append(this.content, rhs.content)
				.append(this.mobileNo, rhs.mobileNo).append(this.createtime, rhs.createtime)
				.append(this.status, rhs.status).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.msgId).append(this.content).append(this.mobileNo)
				.append(this.createtime).append(this.status).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("msgId", this.msgId).append("content", this.content)
				.append("mobileNo", this.mobileNo).append("createtime", this.createtime).append("status", this.status)
				.toString();
	}
}
