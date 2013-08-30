package com.cyjt.oa.model.personal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.AppUser;

@Entity
@Table(name = "duty")
public class Duty extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3354017521725812101L;
	private Long dutyId;
	private String fullname;
	private Date startTime;
	private Date endTime;
	private DutySystem dutySystem;
	private AppUser appUser;

	public Duty() {
	}

	public Duty(Long in_dutyId) {
		this.setDutyId(in_dutyId);
	}

	@Id
	@GeneratedValue
	@Column(name = "dutyId", unique = true, nullable = false)
	public Long getDutyId() {
		return this.dutyId;
	}

	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systemId")
	public DutySystem getDutySystem() {
		return this.dutySystem;
	}

	public void setDutySystem(DutySystem dutySystem) {
		this.dutySystem = dutySystem;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "fullname", nullable = false, length = 32)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "startTime", nullable = false, length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Transient
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

	@Transient
	public Long getSystemId() {
		return this.getDutySystem() == null ? null : this.getDutySystem()
				.getSystemId();
	}

	public void setSystemId(Long aValue) {
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Duty)) {
			return false;
		}
		Duty rhs = (Duty) object;
		return new EqualsBuilder().append(this.dutyId, rhs.dutyId)
				.append(this.fullname, rhs.fullname)
				.append(this.startTime, rhs.startTime)
				.append(this.endTime, rhs.endTime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.dutyId)
				.append(this.fullname).append(this.startTime)
				.append(this.endTime).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("dutyId", this.dutyId)
				.append("fullname", this.fullname)
				.append("startTime", this.startTime)
				.append("endTime", this.endTime).toString();
	}
}
