package com.palmelf.eoffice.model.system;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "diary")
public class Diary extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7162319865067459705L;

	@Expose
	private Long diaryId;

	@Expose
	private Date dayTime;

	@Expose
	private String content;

	@Expose
	private Short diaryType;
	private AppUser appUser;

	public Diary() {
	}

	public Diary(Long in_diaryId) {
		this.setDiaryId(in_diaryId);
	}

	@Id
	@GeneratedValue
	@Column(name = "diaryId", unique = true, nullable = false)
	public Long getDiaryId() {
		return this.diaryId;
	}

	public void setDiaryId(Long diaryId) {
		this.diaryId = diaryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "dayTime", nullable = false, length = 19)
	public Date getDayTime() {
		return this.dayTime;
	}

	public void setDayTime(Date dayTime) {
		this.dayTime = dayTime;
	}

	@Lob
	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "diaryType", nullable = false)
	public Short getDiaryType() {
		return this.diaryType;
	}

	public void setDiaryType(Short diaryType) {
		this.diaryType = diaryType;
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

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Diary)) {
			return false;
		}
		Diary rhs = (Diary) object;
		return new EqualsBuilder().append(this.diaryId, rhs.diaryId).append(this.dayTime, rhs.dayTime)
				.append(this.content, rhs.content).append(this.diaryType, rhs.diaryType).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.diaryId).append(this.dayTime)
				.append(this.content).append(this.diaryType).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("diaryId", this.diaryId).append("dayTime", this.dayTime)
				.append("content", this.content).append("diaryType", this.diaryType).toString();
	}

}
