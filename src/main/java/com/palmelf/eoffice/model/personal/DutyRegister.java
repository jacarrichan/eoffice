package com.palmelf.eoffice.model.personal;

import java.text.SimpleDateFormat;
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

import com.google.gson.annotations.Expose;
import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.AppUser;

@Entity
@Table(name = "duty_register")
public class DutyRegister extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6533376961612973527L;

	public static final Short SIGN_IN = 1;

	public static final Short SIGN_OFF = 2;

	public static final Short REG_FLAG_NORMAL = 1;

	public static final Short REG_FLAG_LATE = 2;

	public static final Short REG_FLAG_EARLY_OFF = 3;

	public static final Short REG_FLAG_RELAX = 4;

	public static final Short REG_FLAG_TRUANCY = 5;

	public static final Short REG_FLAG_HOLIDAY = 6;

	@Expose
	private Long registerId;

	@Expose
	private Date registerDate;

	@Expose
	private Short regFlag;

	@Expose
	private Integer regMins;

	@Expose
	private String reason;

	@Expose
	private Integer dayOfWeek;

	@Expose
	private Short inOffFlag;

	@Expose
	private String fullname;
	private AppUser appUser;
	private DutySection dutySection;

	public DutyRegister() {
	}

	public DutyRegister(Long in_registerId) {
		this.setRegisterId(in_registerId);
	}

	@Id
	@GeneratedValue
	@Column(name = "registerId", unique = true, nullable = false)
	public Long getRegisterId() {
		return this.registerId;
	}

	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", insertable=false,updatable=false)
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sectionId", insertable=false,updatable=false)
	public DutySection getDutySection() {
		return this.dutySection;
	}

	public void setDutySection(DutySection dutySection) {
		this.dutySection = dutySection;
	}

	@Column(name = "registerDate", nullable = false, length = 19)
	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "regFlag", nullable = false)
	public Short getRegFlag() {
		return this.regFlag;
	}

	public void setRegFlag(Short regFlag) {
		this.regFlag = regFlag;
	}

	@Column(name = "regMins", nullable = false)
	public Integer getRegMins() {
		return this.regMins;
	}

	public void setRegMins(Integer regMins) {
		this.regMins = regMins;
	}

	@Column(name = "reason", length = 128)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "dayOfWeek", nullable = false)
	public Integer getDayOfWeek() {
		return this.dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@Column(name = "inOffFlag", nullable = false)
	public Short getInOffFlag() {
		return this.inOffFlag;
	}

	public void setInOffFlag(Short inOffFlag) {
		this.inOffFlag = inOffFlag;
	}

	@Column(name = "fullname", nullable = false, length = 32)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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
		if (!(object instanceof DutyRegister)) {
			return false;
		}
		DutyRegister rhs = (DutyRegister) object;
		return new EqualsBuilder().append(this.registerId, rhs.registerId).append(this.registerDate, rhs.registerDate)
				.append(this.regFlag, rhs.regFlag).append(this.regMins, rhs.regMins).append(this.reason, rhs.reason)
				.append(this.dayOfWeek, rhs.dayOfWeek).append(this.inOffFlag, rhs.inOffFlag).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.registerId).append(this.registerDate)
				.append(this.regFlag).append(this.regMins).append(this.reason).append(this.dayOfWeek)
				.append(this.inOffFlag).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("registerId", this.registerId)
				.append("registerDate", this.registerDate).append("regFlag", this.regFlag)
				.append("regMins", this.regMins).append("reason", this.reason).append("dayOfWeek", this.dayOfWeek)
				.append("inOffFlag", this.inOffFlag).toString();
	}

	public String getRegisterTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(this.registerDate);
	}

	public void setRegisterTime(String registerTime){
		
	}
}
