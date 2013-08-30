package com.cyjt.oa.model.hrm;

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
@Table(name = "hire_issue")
public class HireIssue extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2246606618290525141L;
	public static Short PASS_CHECK = 1;
	public static Short NOTPASS_CHECK = 2;
	public static Short NOTYETPASS_CHECK = 0;
	private Long hireId;
	private String title;
	private Date startDate;
	private Date endDate;
	private Integer hireCount;
	private String jobName;
	private String jobCondition;
	private String regFullname;
	private Date regDate;
	private String modifyFullname;
	private Date modifyDate;
	private String checkFullname;
	private String checkOpinion;
	private Date checkDate;
	private Short status;
	private String memo;

	public HireIssue() {
	}

	public HireIssue(Long in_hireId) {
		this.setHireId(in_hireId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "hireId", unique = true, nullable = false)
	public Long getHireId() {
		return this.hireId;
	}

	public void setHireId(Long hireId) {
		this.hireId = hireId;
	}

	@Column(name = "title", nullable = false, length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "startDate", nullable = false, length = 19)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "endDate", nullable = false, length = 19)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "hireCount", nullable = false)
	public Integer getHireCount() {
		return this.hireCount;
	}

	public void setHireCount(Integer hireCount) {
		this.hireCount = hireCount;
	}

	@Column(name = "jobName", nullable = false, length = 128)
	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Column(name = "jobCondition", length = 1024)
	public String getJobCondition() {
		return this.jobCondition;
	}

	public void setJobCondition(String jobCondition) {
		this.jobCondition = jobCondition;
	}

	@Column(name = "regFullname", nullable = false, length = 128)
	public String getRegFullname() {
		return this.regFullname;
	}

	public void setRegFullname(String regFullname) {
		this.regFullname = regFullname;
	}

	@Column(name = "regDate", nullable = false, length = 19)
	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Column(name = "modifyFullname", length = 32)
	public String getModifyFullname() {
		return this.modifyFullname;
	}

	public void setModifyFullname(String modifyFullname) {
		this.modifyFullname = modifyFullname;
	}

	@Column(name = "modifyDate", length = 19)
	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "checkFullname", length = 32)
	public String getCheckFullname() {
		return this.checkFullname;
	}

	public void setCheckFullname(String checkFullname) {
		this.checkFullname = checkFullname;
	}

	@Column(name = "checkOpinion", length = 512)
	public String getCheckOpinion() {
		return this.checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}

	@Column(name = "checkDate", length = 19)
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "memo", length = 1024)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof HireIssue)) {
			return false;
		}
		HireIssue rhs = (HireIssue) object;
		return new EqualsBuilder().append(this.hireId, rhs.hireId).append(this.title, rhs.title)
				.append(this.startDate, rhs.startDate).append(this.endDate, rhs.endDate)
				.append(this.hireCount, rhs.hireCount).append(this.jobName, rhs.jobName)
				.append(this.jobCondition, rhs.jobCondition).append(this.regFullname, rhs.regFullname)
				.append(this.regDate, rhs.regDate).append(this.modifyFullname, rhs.modifyFullname)
				.append(this.modifyDate, rhs.modifyDate).append(this.checkFullname, rhs.checkFullname)
				.append(this.checkOpinion, rhs.checkOpinion).append(this.checkDate, rhs.checkDate)
				.append(this.status, rhs.status).append(this.memo, rhs.memo).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.hireId).append(this.title).append(this.startDate)
				.append(this.endDate).append(this.hireCount).append(this.jobName).append(this.jobCondition)
				.append(this.regFullname).append(this.regDate).append(this.modifyFullname).append(this.modifyDate)
				.append(this.checkFullname).append(this.checkOpinion).append(this.checkDate).append(this.status)
				.append(this.memo).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("hireId", this.hireId).append("title", this.title)
				.append("startDate", this.startDate).append("endDate", this.endDate)
				.append("hireCount", this.hireCount).append("jobName", this.jobName)
				.append("jobCondition", this.jobCondition).append("regFullname", this.regFullname)
				.append("regDate", this.regDate).append("modifyFullname", this.modifyFullname)
				.append("modifyDate", this.modifyDate).append("checkFullname", this.checkFullname)
				.append("checkOpinion", this.checkOpinion).append("checkDate", this.checkDate)
				.append("status", this.status).append("memo", this.memo).toString();
	}
}
