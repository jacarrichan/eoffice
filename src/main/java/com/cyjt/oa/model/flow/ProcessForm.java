package com.cyjt.oa.model.flow;

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

import com.cyjt.core.model.BaseModel;

@Entity
@Table(name = "process_form")
public class ProcessForm extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8511053645947947361L;
	private Long formId;
	private String activityName;
	private Integer sn = Integer.valueOf(1);
	private Date createtime;
	private Long creatorId;
	private String creatorName;
	private ProcessRun processRun;
	private Set<FormData> formDatas = new HashSet<FormData>();

	public ProcessForm() {
	}

	public ProcessForm(Long in_formId) {
		this.setFormId(in_formId);
	}

	@Id
	@GeneratedValue
	@Column(name = "formId", unique = true, nullable = false)
	public Long getFormId() {
		return this.formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "runId")
	public ProcessRun getProcessRun() {
		return this.processRun;
	}

	public void setProcessRun(ProcessRun processRun) {
		this.processRun = processRun;
	}

	@Column(name = "activityName", nullable = false, length = 128)
	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		if (this.createtime == null) {
			this.createtime = new Date();
		}
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "sn", nullable = false)
	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	@Column(name = "creatorId")
	public Long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	@Column(name = "creatorName", length = 64)
	public String getCreatorName() {
		return this.creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "processForm")
	public Set<FormData> getFormDatas() {
		return this.formDatas;
	}

	public void setFormDatas(Set<FormData> formDatas) {
		this.formDatas = formDatas;
	}
	@Transient
	public Long getRunId() {
		return this.getProcessRun() == null ? null : this.getProcessRun().getRunId();
	}

	public void setRunId(Long aValue) {
		if (aValue == null) {
			this.processRun = null;
		} else if (this.processRun == null) {
			this.processRun = new ProcessRun(aValue);
			this.processRun.setVersion(new Integer(0));
		} else {
			this.processRun.setRunId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ProcessForm)) {
			return false;
		}
		ProcessForm rhs = (ProcessForm) object;
		return new EqualsBuilder().append(this.formId, rhs.formId).append(this.activityName, rhs.activityName)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.formId).append(this.activityName).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("formId", this.formId).append("activityName", this.activityName)
				.toString();
	}
}
