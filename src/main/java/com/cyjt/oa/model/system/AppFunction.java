package com.cyjt.oa.model.system;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "app_function")
public class AppFunction extends BaseModel {
	private static final long serialVersionUID = 1L;
	private Long functionId;
	private String funKey;
	private String funName;
	private Set<FunUrl> funUrls = new HashSet<FunUrl>();

	public AppFunction() {
	}

	public AppFunction(String funKey, String funName) {
		this.funKey = funKey;
		this.funName = funName;
	}

	public AppFunction(Long in_functionId) {
		this.setFunctionId(in_functionId);
	}

	@Id
	@GeneratedValue
	@Column(name = "functionId", unique = true, nullable = false)
	public Long getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}

	@Column(name = "funKey", nullable = false, length = 64)
	public String getFunKey() {
		return this.funKey;
	}

	public void setFunKey(String funKey) {
		this.funKey = funKey;
	}

	@Column(name = "funName", nullable = false, length = 128)
	public String getFunName() {
		return this.funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appFunction")
	public Set<FunUrl> getFunUrls() {
		return this.funUrls;
	}

	public void setFunUrls(Set<FunUrl> funUrls) {
		this.funUrls = funUrls;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AppFunction)) {
			return false;
		}
		AppFunction rhs = (AppFunction) object;
		return new EqualsBuilder().append(this.functionId, rhs.functionId).append(this.funKey, rhs.funKey)
				.append(this.funName, rhs.funName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.functionId).append(this.funKey)
				.append(this.funName).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("functionId", this.functionId).append("funKey", this.funKey)
				.append("funName", this.funName).toString();
	}
}
