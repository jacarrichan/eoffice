package com.cyjt.oa.model.personal;

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
@Table(name = "holiday_record")
public class HolidayRecord extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2888995089495133111L;
	private Long recordId;
	private Date startTime;
	private Date endTime;
	private String descp;
	private Long userId;
	private String fullname;
	private Short isAll;
	public static final Short IS_ALL_HOLIDAY = 1;

	public static final Short IS_PERSONAL_HOLIDAY = 0;

	public HolidayRecord() {
	}

	public HolidayRecord(Long in_recordId) {
		this.setRecordId(in_recordId);
	}

	@Id
	@GeneratedValue
	@Column(name = "recordId", unique = true, nullable = false)
	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	@Column(name = "startTime", nullable = false, length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime", nullable = false, length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "descp", nullable = false, length = 512)
	public String getDescp() {
		return this.descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "fullname", length = 32)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "isAll", nullable = false)
	public Short getIsAll() {
		return this.isAll;
	}

	public void setIsAll(Short isAll) {
		this.isAll = isAll;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof HolidayRecord)) {
			return false;
		}
		HolidayRecord rhs = (HolidayRecord) object;
		return new EqualsBuilder().append(this.recordId, rhs.recordId).append(this.startTime, rhs.startTime)
				.append(this.endTime, rhs.endTime).append(this.userId, rhs.userId).append(this.isAll, rhs.isAll)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.recordId).append(this.startTime)
				.append(this.endTime).append(this.userId).append(this.isAll).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("recordId", this.recordId).append("startTime", this.startTime)
				.append("endTime", this.endTime).append("userId", this.userId).append("isAll", this.isAll).toString();
	}
}
