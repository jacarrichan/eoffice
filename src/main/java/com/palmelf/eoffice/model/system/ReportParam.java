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
@Table(name = "report_param")
public class ReportParam extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2000237381518047086L;
	private Long paramId;
	private String paramName;
	private String paramKey;
	private String defaultVal;
	private String paramType;
	private Integer sn;
	private ReportTemplate reportTemplate;
	private String paramTypeStr;

	public ReportParam() {
	}

	public ReportParam(Long in_paramId) {
		this.setParamId(in_paramId);
	}

	@Id
	@GeneratedValue
	@Column(name = "paramId", unique = true, nullable = false)
	public Long getParamId() {
		return this.paramId;
	}

	public void setParamId(Long paramId) {
		this.paramId = paramId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reportId")
	public ReportTemplate getReportTemplate() {
		return this.reportTemplate;
	}

	public void setReportTemplate(ReportTemplate reportTemplate) {
		this.reportTemplate = reportTemplate;
	}

	@Column(name = "paramName", nullable = false, length = 64)
	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Column(name = "paramKey", nullable = false, length = 64)
	public String getParamKey() {
		return this.paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	@Column(name = "defaultVal", length = 128)
	public String getDefaultVal() {
		return this.defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

	@Column(name = "paramType", nullable = false, length = 32)
	public String getParamType() {
		return this.paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	@Column(name = "sn", nullable = false)
	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	@Column(name = "paramTypeStr")
	public String getParamTypeStr() {
		return this.paramTypeStr;
	}

	public void setParamTypeStr(String paramTypeStr) {
		this.paramTypeStr = paramTypeStr;
	}
	@Transient
	public Long getReportId() {
		return this.getReportTemplate() == null ? null : this.getReportTemplate().getReportId();
	}

	public void setReportId(Long aValue) {
		if (aValue == null) {
			this.reportTemplate = null;
		} else if (this.reportTemplate == null) {
			this.reportTemplate = new ReportTemplate(aValue);
			this.reportTemplate.setVersion(new Integer(0));
		} else {
			this.reportTemplate.setReportId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ReportParam)) {
			return false;
		}
		ReportParam rhs = (ReportParam) object;
		return new EqualsBuilder().append(this.paramId, rhs.paramId).append(this.paramName, rhs.paramName)
				.append(this.paramKey, rhs.paramKey).append(this.defaultVal, rhs.defaultVal)
				.append(this.paramType, rhs.paramType).append(this.sn, rhs.sn).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.paramId).append(this.paramName)
				.append(this.paramKey).append(this.defaultVal).append(this.paramType).append(this.sn).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("paramId", this.paramId).append("paramName", this.paramName)
				.append("paramKey", this.paramKey).append("defaultVal", this.defaultVal)
				.append("paramType", this.paramType).append("sn", this.sn).toString();
	}
}
