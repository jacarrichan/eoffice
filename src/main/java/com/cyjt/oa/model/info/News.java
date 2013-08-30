package com.cyjt.oa.model.info;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "news")
public class News extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3667698803444375443L;
	public static Short ISDESKNEWS = 1;
	public static Short NOTDESKNEWS = 0;

	@Expose
	private Long newsId;

	@Expose
	private String subjectIcon;

	@Expose
	private String subject;

	@Expose
	private String author;

	@Expose
	private Date createtime;

	@Expose
	private Integer replyCounts;

	@Expose
	private Integer viewCounts;

	@Expose
	private String content;

	@Expose
	private Date updateTime;

	@Expose
	private Short status;

	@Expose
	private NewsType newsType;

	@Expose
	private String issuer;

	@Expose
	private Short isDeskImage;
	private Set<NewsComment> newsComments = new HashSet<NewsComment>();

	public News() {
	}

	public News(Long in_newsId) {
		this.setNewsId(in_newsId);
	}

	@Id
	@GeneratedValue
	@Column(name = "newsId", unique = true, nullable = false)
	public Long getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId")
	public NewsType getNewsType() {
		return this.newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	@Column(name = "subjectIcon", length = 128)
	public String getSubjectIcon() {
		return this.subjectIcon;
	}

	public void setSubjectIcon(String subjectIcon) {
		this.subjectIcon = subjectIcon;
	}

	@Column(name = "subject", nullable = false, length = 128)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "author", nullable = false, length = 32)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "replyCounts")
	public Integer getReplyCounts() {
		return this.replyCounts;
	}

	public void setReplyCounts(Integer replyCounts) {
		this.replyCounts = replyCounts;
	}

	@Column(name = "viewCounts")
	public Integer getViewCounts() {
		return this.viewCounts;
	}

	public void setViewCounts(Integer viewCounts) {
		this.viewCounts = viewCounts;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "issuer", nullable = false, length = 32)
	public String getIssuer() {
		return this.issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	@Column(name = "updateTime", nullable = false, length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "isDeskImage")
	public Short getIsDeskImage() {
		return this.isDeskImage;
	}

	public void setIsDeskImage(Short isDeskImage) {
		this.isDeskImage = isDeskImage;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "news")
	public Set<NewsComment> getNewsComments() {
		return this.newsComments;
	}

	public void setNewsComments(Set<NewsComment> newsComments) {
		this.newsComments = newsComments;
	}
	@Transient
	public Long getTypeId() {
		return this.getNewsType() == null ? null : this.getNewsType().getTypeId();
	}
	@Transient
	public void setTypeId(Long aValue) {
		if (aValue == null) {
			this.newsType = null;
		} else if (this.newsType == null) {
			this.newsType = new NewsType();
			this.newsType.setTypeId(aValue);
		} else {
			this.newsType.setTypeId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof News)) {
			return false;
		}
		News rhs = (News) object;
		return new EqualsBuilder().append(this.newsId, rhs.newsId).append(this.subjectIcon, rhs.subjectIcon)
				.append(this.subject, rhs.subject).append(this.author, rhs.author)
				.append(this.createtime, rhs.createtime).append(this.replyCounts, rhs.replyCounts)
				.append(this.viewCounts, rhs.viewCounts).append(this.content, rhs.content)
				.append(this.updateTime, rhs.updateTime).append(this.status, rhs.status)
				.append(this.issuer, rhs.issuer).append(this.isDeskImage, rhs.isDeskImage).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.newsId).append(this.subjectIcon)
				.append(this.subject).append(this.author).append(this.createtime).append(this.replyCounts)
				.append(this.viewCounts).append(this.content).append(this.updateTime).append(this.status)
				.append(this.issuer).append(this.isDeskImage).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("newsId", this.newsId).append("subjectIcon", this.subjectIcon)
				.append("subject", this.subject).append("author", this.author).append("createtime", this.createtime)
				.append("replyCounts", this.replyCounts).append("viewCounts", this.viewCounts)
				.append("content", this.content).append("updateTime", this.updateTime).append("status", this.status)
				.append("issuer", this.issuer).append("isDeskImage", this.isDeskImage).toString();
	}

}
