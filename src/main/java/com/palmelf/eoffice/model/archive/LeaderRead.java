package com.palmelf.eoffice.model.archive;

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

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "leader_read")
public class LeaderRead extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1443474835563715967L;
	public static Short NOT_CHECK = 0;
	public static Short IS_PASS = 1;
	public static Short NOT_PASS = 2;
	private Long readId;
	private String leaderName;
	private Long userId;
	private String leaderOpinion;
	private Date createtime;
	private Integer depLevel;
	private String depName;
	private Short isPass;
	private String checkName;
	private Archives archives;

	public LeaderRead() {
	}

	public LeaderRead(Long in_readId) {
		this.setReadId(in_readId);
	}
	@Transient
	public Long getArchivesId() {
		return this.getArchives() == null ? null : this.getArchives().getArchivesId();
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "readId", unique = true, nullable = false)
	public Long getReadId() {
		return this.readId;
	}

	public void setReadId(Long readId) {
		this.readId = readId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "archivesId")
	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	@Column(name = "leaderName", nullable = false, length = 64)
	public String getLeaderName() {
		return this.leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "leaderOpinion", length = 128)
	public String getLeaderOpinion() {
		return this.leaderOpinion;
	}

	public void setLeaderOpinion(String leaderOpinion) {
		this.leaderOpinion = leaderOpinion;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "depLevel")
	public Integer getDepLevel() {
		return this.depLevel;
	}

	public void setDepLevel(Integer depLevel) {
		this.depLevel = depLevel;
	}

	@Column(name = "depName", length = 128)
	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	@Column(name = "isPass", nullable = false)
	public Short getIsPass() {
		return this.isPass;
	}

	public void setIsPass(Short isPass) {
		this.isPass = isPass;
	}

	@Column(name = "checkName", length = 128)
	public String getCheckName() {
		return this.checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public void setArchivesId(Long aValue) {
		if (aValue == null) {
			this.archives = null;
		} else if (this.archives == null) {
			this.archives = new Archives(aValue);
			this.archives.setVersion(new Integer(0));
		} else {
			this.archives.setArchivesId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof LeaderRead)) {
			return false;
		}
		LeaderRead rhs = (LeaderRead) object;
		return new EqualsBuilder().append(this.readId, rhs.readId).append(this.leaderName, rhs.leaderName)
				.append(this.userId, rhs.userId).append(this.leaderOpinion, rhs.leaderOpinion)
				.append(this.createtime, rhs.createtime).append(this.depLevel, rhs.depLevel)
				.append(this.depName, rhs.depName).append(this.isPass, rhs.isPass)
				.append(this.checkName, rhs.checkName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.readId).append(this.leaderName)
				.append(this.userId).append(this.leaderOpinion).append(this.createtime).append(this.depLevel)
				.append(this.depName).append(this.isPass).append(this.checkName).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("readId", this.readId).append("leaderName", this.leaderName)
				.append("userId", this.userId).append("leaderOpinion", this.leaderOpinion)
				.append("createtime", this.createtime).append("depLevel", this.depLevel)
				.append("depName", this.depName).append("isPass", this.isPass).append("checkName", this.checkName)
				.toString();
	}
}
