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

import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.AppUser;

@Entity
@Table(name = "news_comment")
public class NewsComment extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5327157399325350467L;

	public static final Short STATUS_PASS = 1;

	public static final Short STATUS_NONE = 0;
	private Long commentId;
	private String content;
	private Date createtime;
	private String fullname;
	private News news;
	private Short status;
	private AppUser appUser;

	public NewsComment() {
	}

	public NewsComment(Long in_commentId) {
		this.setCommentId(in_commentId);
	}

	@Id
	@GeneratedValue
	@Column(name = "commentId", unique = true, nullable = false)
	public Long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "newsId")
	public News getNews() {
		return this.news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "content", nullable = false, length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "fullname", nullable = false, length = 32)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	@Transient
	public Long getNewsId() {
		return this.getNews() == null ? null : this.getNews().getNewsId();
	}

	public void setNewsId(Long aValue) {
		if (aValue == null) {
			this.news = null;
		} else if (this.news == null) {
			this.news = new News(aValue);
			this.news.setVersion(new Integer(0));
		} else {
			this.news.setNewsId(aValue);
		}
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
		if (!(object instanceof NewsComment)) {
			return false;
		}
		NewsComment rhs = (NewsComment) object;
		return new EqualsBuilder().append(this.commentId, rhs.commentId).append(this.content, rhs.content)
				.append(this.createtime, rhs.createtime).append(this.fullname, rhs.fullname)
				.append(this.status, rhs.status).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.commentId).append(this.content)
				.append(this.createtime).append(this.fullname).append(this.status).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("commentId", this.commentId).append("content", this.content)
				.append("createtime", this.createtime).append("fullname", this.fullname).append("status", this.status)
				.toString();
	}
}
