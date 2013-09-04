package com.palmelf.eoffice.model.system;

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
@Table(name = "system_log")
public class SystemLog extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 991016334474843498L;
	private Long logId;
	private String username;
	private Long userId;
	private Date createtime;
	private String exeOperation;

	public SystemLog() {
	}

	public SystemLog(Long in_logId) {
		this.setLogId(in_logId);
	}

	@Id
	@GeneratedValue
	@Column(name = "logId", unique = true, nullable = false)
	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "username", nullable = false, length = 128)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "exeOperation", nullable = false, length = 512)
	public String getExeOperation() {
		return this.exeOperation;
	}

	public void setExeOperation(String exeOperation) {
		this.exeOperation = exeOperation;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SystemLog)) {
			return false;
		}
		SystemLog rhs = (SystemLog) object;
		return new EqualsBuilder().append(this.logId, rhs.logId).append(this.username, rhs.username)
				.append(this.userId, rhs.userId).append(this.createtime, rhs.createtime)
				.append(this.exeOperation, rhs.exeOperation).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.logId).append(this.username).append(this.userId)
				.append(this.createtime).append(this.exeOperation).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("logId", this.logId).append("username", this.username)
				.append("userId", this.userId).append("createtime", this.createtime)
				.append("exeOperation", this.exeOperation).toString();
	}
}
