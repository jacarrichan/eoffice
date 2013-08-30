package com.cyjt.oa.model.personal;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.time.DateUtils;

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "duty_section")
public class DutySection extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8309739481251024023L;
	private Long sectionId;
	private String sectionName;
	private Date startSignin;
	private Date dutyStartTime;
	private Date endSignin;
	private Date earlyOffTime;
	private Date dutyEndTime;
	private Date signOutTime;
	private String startSignin1;
	private String dutyStartTime1;
	private String endSignin1;
	private String earlyOffTime1;
	private String dutyEndTime1;
	private String signOutTime1;
	public final String datePattern = "yyyy-MM-dd HH:mm:ss";

	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

	public DutySection() {
	}

	public DutySection(Long in_sectionId) {
		this.setSectionId(in_sectionId);
	}

	@Id
	@GeneratedValue
	@Column(name = "sectionId", unique = true, nullable = false)
	public Long getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	@Column(name = "sectionName", nullable = false, length = 64)
	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	@Column(name = "startSignin", nullable = false, length = 19)
	public Date getStartSignin() {
		return this.startSignin;
	}

	public void setStartSignin(Date startSignin) {
		this.startSignin = startSignin;
	}

	@Column(name = "dutyStartTime", nullable = false, length = 19)
	public Date getDutyStartTime() {
		return this.dutyStartTime;
	}

	public void setDutyStartTime(Date dutyStartTime) {
		this.dutyStartTime = dutyStartTime;
	}

	@Column(name = "endSignin", nullable = false, length = 19)
	public Date getEndSignin() {
		return this.endSignin;
	}

	public void setEndSignin(Date endSignin) {
		this.endSignin = endSignin;
	}

	@Column(name = "earlyOffTime", nullable = false, length = 19)
	public Date getEarlyOffTime() {
		return this.earlyOffTime;
	}

	public void setEarlyOffTime(Date earlyOffTime) {
		this.earlyOffTime = earlyOffTime;
	}

	@Column(name = "dutyEndTime", nullable = false, length = 19)
	public Date getDutyEndTime() {
		return this.dutyEndTime;
	}

	public void setDutyEndTime(Date dutyEndTime) {
		this.dutyEndTime = dutyEndTime;
	}

	@Column(name = "signOutTime", nullable = false, length = 19)
	public Date getSignOutTime() {
		return this.signOutTime;
	}

	public void setSignOutTime(Date signOutTime) {
		this.signOutTime = signOutTime;
	}

	public void setStartSignin1(String inVal) {
		this.startSignin1 = inVal;
		String finalVal = "1900-01-01 " + inVal + ":00";
		try {
			this.startSignin = DateUtils.parseDate(finalVal, new String[] { "yyyy-MM-dd HH:mm:ss" });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getStartSignin1() {
		return this.sdf.format(this.startSignin);
	}

	public String getDutyStartTime1() {
		return this.sdf.format(this.dutyStartTime);
	}

	public String getEndSignin1() {
		return this.sdf.format(this.endSignin);
	}

	public String getEarlyOffTime1() {
		return this.sdf.format(this.earlyOffTime);
	}

	public String getDutyEndTime1() {
		return this.sdf.format(this.dutyEndTime);
	}

	public String getSignOutTime1() {
		return this.sdf.format(this.signOutTime);
	}

	public void setDutyStartTime1(String inVal) {
		this.dutyStartTime1 = inVal;
		String finalVal = "1900-01-01 " + inVal + ":00";
		try {
			this.dutyStartTime = DateUtils.parseDate(finalVal, new String[] { "yyyy-MM-dd HH:mm:ss" });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEndSignin1(String inVal) {
		this.endSignin1 = inVal;
		String finalVal = "1900-01-01 " + inVal + ":00";
		try {
			this.endSignin = DateUtils.parseDate(finalVal, new String[] { "yyyy-MM-dd HH:mm:ss" });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEarlyOffTime1(String inVal) {
		this.earlyOffTime1 = inVal;
		String finalVal = "1900-01-01 " + inVal + ":00";
		try {
			this.earlyOffTime = DateUtils.parseDate(finalVal, new String[] { "yyyy-MM-dd HH:mm:ss" });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setDutyEndTime1(String inVal) {
		this.dutyEndTime1 = inVal;
		String finalVal = "1900-01-01 " + inVal + ":00";
		try {
			this.dutyEndTime = DateUtils.parseDate(finalVal, new String[] { "yyyy-MM-dd HH:mm:ss" });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setSignOutTime1(String inVal) {
		this.signOutTime1 = inVal;
		String finalVal = "1900-01-01 " + inVal + ":00";
		try {
			this.signOutTime = DateUtils.parseDate(finalVal, new String[] { "yyyy-MM-dd HH:mm:ss" });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DutySection)) {
			return false;
		}
		DutySection rhs = (DutySection) object;
		return new EqualsBuilder().append(this.sectionId, rhs.sectionId).append(this.startSignin, rhs.startSignin)
				.append(this.dutyStartTime, rhs.dutyStartTime).append(this.endSignin, rhs.endSignin)
				.append(this.earlyOffTime, rhs.earlyOffTime).append(this.dutyEndTime, rhs.dutyEndTime)
				.append(this.signOutTime, rhs.signOutTime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.sectionId).append(this.startSignin)
				.append(this.dutyStartTime).append(this.endSignin).append(this.earlyOffTime).append(this.dutyEndTime)
				.append(this.signOutTime).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("sectionId", this.sectionId).append("startSignin", this.startSignin)
				.append("dutyStartTime", this.dutyStartTime).append("endSignin", this.endSignin)
				.append("earlyOffTime", this.earlyOffTime).append("dutyEndTime", this.dutyEndTime)
				.append("signOutTime", this.signOutTime).toString();
	}
}
