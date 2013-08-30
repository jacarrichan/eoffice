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

import com.google.gson.annotations.Expose;
import com.cyjt.core.model.BaseModel;
import com.cyjt.oa.model.system.AppUser;

@Entity
@Table(name = "process_run")
public class ProcessRun extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 471984545094207655L;

	public static final Short RUN_STATUS_INIT = 0;

	public static final Short RUN_STATUS_RUNNING = 1;

	public static final Short RUN_STATUS_FINISHED = 2;
	private Long runId;

	@Expose
	private String subject;

	@Expose
	private String creator;

	@Expose
	private Date createtime;

	@Expose
	private ProDefinition proDefinition;

	@Expose
	private String piId;

	@Expose
	private Short runStatus = ProcessRun.RUN_STATUS_INIT;
	private AppUser appUser;
	private Set<ProcessForm> processForms = new HashSet<ProcessForm>();

	public ProcessRun() {
	}

	public ProcessRun(Long in_runId) {
		this.setRunId(in_runId);
	}

	@Id
	@GeneratedValue
	@Column(name = "runId", unique = true, nullable = false)
	public Long getRunId() {
		return this.runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "defId")
	public ProDefinition getProDefinition() {
		return this.proDefinition;
	}

	public void setProDefinition(ProDefinition proDefinition) {
		this.proDefinition = proDefinition;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "subject", nullable = false, length = 256)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "creator", length = 128)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "piId", length = 64)
	public String getPiId() {
		return this.piId;
	}

	public void setPiId(String piId) {
		this.piId = piId;
	}

	@Column(name = "runStatus", nullable = false)
	public Short getRunStatus() {
		return this.runStatus;
	}

	public void setRunStatus(Short runStatus) {
		this.runStatus = runStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "processRun")
	public Set<ProcessForm> getProcessForms() {
		return this.processForms;
	}

	public void setProcessForms(Set<ProcessForm> processForms) {
		this.processForms = processForms;
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
		if (!(object instanceof ProcessRun)) {
			return false;
		}
		ProcessRun rhs = (ProcessRun) object;
		return new EqualsBuilder().append(this.runId, rhs.runId).append(this.subject, rhs.subject)
				.append(this.creator, rhs.creator).append(this.piId, rhs.piId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.runId).append(this.subject).append(this.creator)
				.append(this.piId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("runId", this.runId).append("subject", this.subject)
				.append("creator", this.creator).append("piId", this.piId).toString();
	}

}
