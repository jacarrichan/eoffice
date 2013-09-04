package com.palmelf.eoffice.model.personal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.AppUser;

@Entity
@Table(name = "errands_register")
public class ErrandsRegister extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7253225475485206671L;
	private Long dateId;
	private Long approvalId;
	private String descp;
	private Date startTime;
	private Date endTime;
	private Short status;
	private String approvalOption;
	private String approvalName;
	private Short flag;
	private AppUser appUser;
	public static final Short STATUS_UNCHECKED = 0;

	public static final Short STATUS_APPROVAL = 1;

	public static final Short STATUS_UNAPPROVAL = 2;

	public static final Short FLAG_OVERTIME = 0;

	public static final Short FLAG_LEAVE = 1;

	public static final Short FLAG_OUT = 2;

	public ErrandsRegister() {
	}

	public ErrandsRegister(Long in_dateId) {
		this.setDateId(in_dateId);
	}

	@Id
	@GeneratedValue
	@Column(name = "dateId", unique = true, nullable = false)
	public Long getDateId() {
		return this.dateId;
	}

	public void setDateId(Long dateId) {
		this.dateId = dateId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", insertable=false,updatable=false)
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "approvalId", nullable = false)
	public Long getApprovalId() {
		return this.approvalId;
	}

	public void setApprovalId(Long approvalId) {
		this.approvalId = approvalId;
	}

	@Column(name = "descp", nullable = false, length = 500)
	public String getDescp() {
		return this.descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
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

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "approvalOption", length = 500)
	public String getApprovalOption() {
		return this.approvalOption;
	}

	public void setApprovalOption(String approvalOption) {
		this.approvalOption = approvalOption;
	}

	@Column(name = "approvalName", nullable = false, length = 128)
	public String getApprovalName() {
		return this.approvalName;
	}

	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}

	@Column(name = "flag")
	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(Short flag) {
		this.flag = flag;
	}

	public Long getUserId() {
		return this.getAppUser() == null ? null : this.getAppUser().getUserId();
	}

	public void setUserId(Long aValue) {
		if (aValue == null) {
			this.appUser = null;
		} else if (this.appUser == null) {
			this.appUser = new AppUser(aValue);
			this.appUser.setVersion(new Integer(0));
		} else {
			this.appUser.setUserId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ErrandsRegister)) {
			return false;
		}
		ErrandsRegister rhs = (ErrandsRegister) object;
		return new EqualsBuilder().append(this.dateId, rhs.dateId).append(this.approvalId, rhs.approvalId)
				.append(this.descp, rhs.descp).append(this.startTime, rhs.startTime).append(this.endTime, rhs.endTime)
				.append(this.status, rhs.status).append(this.approvalOption, rhs.approvalOption)
				.append(this.approvalName, rhs.approvalName).append(this.flag, rhs.flag).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.dateId).append(this.approvalId)
				.append(this.descp).append(this.startTime).append(this.endTime).append(this.status)
				.append(this.approvalOption).append(this.approvalName).append(this.flag).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("dateId", this.dateId).append("userId", this.approvalId)
				.append("descp", this.descp).append("startTime", this.startTime).append("endTime", this.endTime)
				.append("status", this.status).append("approvalOption", this.approvalOption)
				.append("approvalName", this.approvalName).append("flag", this.flag).toString();
	}
}
