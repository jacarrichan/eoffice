package com.palmelf.eoffice.model.flow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "form_def")
public class FormDef extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5186158641568697752L;

	public static final Integer DEFAULT_COLUMNS = Integer.valueOf(1);

	@Expose
	private Long formDefId;

	@Expose
	private String formName;

	@Expose
	private Integer columns;

	@Expose
	private Short isEnabled;

	@Expose
	private String activityName;

	@Expose
	private String extDef;

	@Expose
	private String deployId;

	public FormDef() {
	}

	public FormDef(Long in_formDefId) {
		this.setFormDefId(in_formDefId);
	}

	@Id
	@GeneratedValue
	@Column(name = "formDefId", unique = true, nullable = false)
	public Long getFormDefId() {
		return this.formDefId;
	}

	public void setFormDefId(Long formDefId) {
		this.formDefId = formDefId;
	}

	@Column(name = "formName", nullable = false, length = 128)
	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	@Column(name = "columns", nullable = false)
	public Integer getColumns() {
		return this.columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	@Column(name = "isEnabled", nullable = false)
	public Short getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Short isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Column(name = "activityName", nullable = false, length = 128)
	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@Column(name = "extDef")
	public String getExtDef() {
		return this.extDef;
	}

	public void setExtDef(String extDef) {
		this.extDef = extDef;
	}

	@Column(name = "deployId", nullable = false, length = 128)
	public String getDeployId() {
		return this.deployId;
	}

	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof FormDef)) {
			return false;
		}
		FormDef rhs = (FormDef) object;
		return new EqualsBuilder().append(this.formDefId, rhs.formDefId).append(this.formName, rhs.formName)
				.append(this.columns, rhs.columns).append(this.isEnabled, rhs.isEnabled)
				.append(this.activityName, rhs.activityName).append(this.deployId, rhs.deployId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.formDefId).append(this.formName)
				.append(this.columns).append(this.isEnabled).append(this.activityName).append(this.deployId)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("formDefId", this.formDefId).append("formName", this.formName)
				.append("columns", this.columns).append("isEnabled", this.isEnabled)
				.append("activityName", this.activityName).append("deployId", this.deployId).toString();
	}
}
