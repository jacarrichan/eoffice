package com.palmelf.eoffice.model.info;

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

import com.google.gson.annotations.Expose;
import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.AppUser;

@Entity
@Table(name = "app_tips")
public class AppTips extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3646898758520911973L;

	@Expose
	private Long tipsId;

	@Expose
	private String tipsName;

	@Expose
	private String content;

	@Expose
	private Integer disheight;

	@Expose
	private Integer diswidth;

	@Expose
	private Integer disleft;

	@Expose
	private Integer distop;

	@Expose
	private Integer dislevel;

	@Expose
	private Date createTime;
	private AppUser appUser;

	public AppTips() {
	}

	public AppTips(Long in_tipsId) {
		this.setTipsId(in_tipsId);
	}

	@Id
	@GeneratedValue
	@Column(name = "tipsId", unique = true, nullable = false)
	public Long getTipsId() {
		return this.tipsId;
	}

	public void setTipsId(Long tipsId) {
		this.tipsId = tipsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "tipsName", length = 128)
	public String getTipsName() {
		return this.tipsName;
	}

	public void setTipsName(String tipsName) {
		this.tipsName = tipsName;
	}

	@Column(name = "content", length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "disheight")
	public Integer getDisheight() {
		return this.disheight;
	}

	public void setDisheight(Integer disheight) {
		this.disheight = disheight;
	}

	@Column(name = "diswidth")
	public Integer getDiswidth() {
		return this.diswidth;
	}

	public void setDiswidth(Integer diswidth) {
		this.diswidth = diswidth;
	}

	@Column(name = "disleft")
	public Integer getDisleft() {
		return this.disleft;
	}

	public void setDisleft(Integer disleft) {
		this.disleft = disleft;
	}

	@Column(name = "distop")
	public Integer getDistop() {
		return this.distop;
	}

	public void setDistop(Integer distop) {
		this.distop = distop;
	}

	@Column(name = "dislevel")
	public Integer getDislevel() {
		return this.dislevel;
	}

	public void setDislevel(Integer dislevel) {
		this.dislevel = dislevel;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
		if (!(object instanceof AppTips)) {
			return false;
		}
		AppTips rhs = (AppTips) object;
		return new EqualsBuilder().append(this.tipsId, rhs.tipsId).append(this.tipsName, rhs.tipsName)
				.append(this.content, rhs.content).append(this.disheight, rhs.disheight)
				.append(this.diswidth, rhs.diswidth).append(this.disleft, rhs.disleft).append(this.distop, rhs.distop)
				.append(this.dislevel, rhs.dislevel).append(this.createTime, rhs.createTime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.tipsId).append(this.tipsName)
				.append(this.content).append(this.disheight).append(this.diswidth).append(this.disleft)
				.append(this.distop).append(this.dislevel).append(this.createTime).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("tipsId", this.tipsId).append("tipsName", this.tipsName)
				.append("content", this.content).append("disheight", this.disheight).append("diswidth", this.diswidth)
				.append("disleft", this.disleft).append("distop", this.distop).append("dislevel", this.dislevel)
				.append("createTime", this.createTime).toString();
	}
}
