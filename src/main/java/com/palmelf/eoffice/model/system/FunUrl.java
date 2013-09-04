package com.palmelf.eoffice.model.system;

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
@Table(name = "fun_url")
public class FunUrl extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4841085966978964703L;
	private Long urlId;
	private String urlPath;
	private AppFunction appFunction;

	public FunUrl() {
	}

	public FunUrl(String urlPath) {
		this.urlPath = urlPath;
	}

	public FunUrl(Long in_urlId) {
		this.setUrlId(in_urlId);
	}

	@Id
	@GeneratedValue
	@Column(name = "urlId", unique = true, nullable = false)
	public Long getUrlId() {
		return this.urlId;
	}

	public void setUrlId(Long urlId) {
		this.urlId = urlId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "functionId")
	public AppFunction getAppFunction() {
		return this.appFunction;
	}

	public void setAppFunction(AppFunction appFunction) {
		this.appFunction = appFunction;
	}

	@Column(name = "urlPath", nullable = false, length = 128)
	public String getUrlPath() {
		return this.urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	@Transient
	public Long getFunctionId() {
		return this.getAppFunction() == null ? null : this.getAppFunction()
				.getFunctionId();
	}

	public void setFunctionId(Long aValue) {
		if (aValue == null) {
			this.appFunction = null;
		} else if (this.appFunction == null) {
			this.appFunction = new AppFunction(aValue);
			this.appFunction.setVersion(new Integer(0));
		} else {
			this.appFunction.setFunctionId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof FunUrl)) {
			return false;
		}
		FunUrl rhs = (FunUrl) object;
		return new EqualsBuilder().append(this.urlId, rhs.urlId)
				.append(this.urlPath, rhs.urlPath).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.urlId)
				.append(this.urlPath).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("urlId", this.urlId)
				.append("urlPath", this.urlPath).toString();
	}
}
