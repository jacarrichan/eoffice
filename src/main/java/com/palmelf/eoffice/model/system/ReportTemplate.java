package com.palmelf.eoffice.model.system;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "report_template")
public class ReportTemplate extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8142513894037075966L;
	private Long reportId;
	private String title;
	private String descp;
	private String reportLocation;
	private Date createtime;
	private Date updatetime;
	private Short isDefaultIn;
	private String reportKey;

	public ReportTemplate() {
	}

	public ReportTemplate(Long in_reportId) {
		this.setReportId(in_reportId);
	}

	@Id
	@GeneratedValue
	@Column(name = "reportId", unique = true, nullable = false)
	public Long getReportId() {
		return this.reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	@Column(name = "title", nullable = false, length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "descp", nullable = false, length = 500)
	public String getDescp() {
		return this.descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	@Column(name = "reportLocation", nullable = false, length = 128)
	public String getReportLocation() {
		return this.reportLocation;
	}

	public void setReportLocation(String reportLocation) {
		this.reportLocation = reportLocation;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "updatetime", nullable = false, length = 19)
	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "isDefaultIn")
	public Short getIsDefaultIn() {
		return this.isDefaultIn;
	}

	public void setIsDefaultIn(Short isDefaultIn) {
		this.isDefaultIn = isDefaultIn;
	}

	@Column(name = "reportKey", unique = true, length = 128)
	public String getReportKey() {
		return this.reportKey;
	}

	public void setReportKey(String reportKey) {
		this.reportKey = reportKey;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ReportTemplate)) {
			return false;
		}
		ReportTemplate rhs = (ReportTemplate) object;
		return new EqualsBuilder().append(this.reportId, rhs.reportId).append(this.title, rhs.title)
				.append(this.descp, rhs.descp).append(this.reportLocation, rhs.reportLocation)
				.append(this.createtime, rhs.createtime).append(this.updatetime, rhs.updatetime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.reportId).append(this.title).append(this.descp)
				.append(this.reportLocation).append(this.createtime).append(this.updatetime).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("reportId", this.reportId).append("title", this.title)
				.append("descp", this.descp).append("reportLocation", this.reportLocation)
				.append("createtime", this.createtime).append("updatetime", this.updatetime).toString();
	}

}
