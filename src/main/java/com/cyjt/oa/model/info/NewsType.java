package com.cyjt.oa.model.info;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "news_type")
public class NewsType extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7497445860355805324L;

	@Expose
	private Long typeId;

	@Expose
	private String typeName;

	@Expose
	private Short sn;
	private Set<News> news;

	@Id
	@GeneratedValue
	@Column(name = "typeId", unique = true, nullable = false)
	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@Column(name = "typeName", nullable = false)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "sn")
	public Short getSn() {
		return this.sn;
	}

	public void setSn(Short sn) {
		this.sn = sn;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "newsType")
	public Set<News> getNews() {
		return this.news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}

}
