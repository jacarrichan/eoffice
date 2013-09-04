package com.palmelf.eoffice.model.communicate;

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
import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.AppUser;

@Entity
@Table(name = "out_mail_user_seting")
public class OutMailUserSeting extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3462957793863636827L;

	@Expose
	private Long id;

	@Expose
	private String userName;

	@Expose
	private String mailAddress;

	@Expose
	private String mailPass;

	@Expose
	private String smtpHost;

	@Expose
	private String smtpPort;

	@Expose
	private String popHost;

	@Expose
	private String popPort;
	private AppUser appUser;
	private Set<OutMail> outMails = new HashSet<OutMail>();

	public OutMailUserSeting() {
	}

	public OutMailUserSeting(Long in_id) {
		this.setId(in_id);
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "userName", length = 128)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "mailAddress", nullable = false, length = 128)
	public String getMailAddress() {
		return this.mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Column(name = "mailPass", nullable = false, length = 128)
	public String getMailPass() {
		return this.mailPass;
	}

	public void setMailPass(String mailPass) {
		this.mailPass = mailPass;
	}

	@Column(name = "smtpHost", nullable = false, length = 128)
	public String getSmtpHost() {
		return this.smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	@Column(name = "smtpPort", nullable = false, length = 64)
	public String getSmtpPort() {
		return this.smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	@Column(name = "popHost", nullable = false, length = 128)
	public String getPopHost() {
		return this.popHost;
	}

	public void setPopHost(String popHost) {
		this.popHost = popHost;
	}

	@Column(name = "popPort", nullable = false, length = 64)
	public String getPopPort() {
		return this.popPort;
	}

	public void setPopPort(String popPort) {
		this.popPort = popPort;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<OutMail> getOutMails() {
		return this.outMails;
	}

	public void setOutMails(Set<OutMail> outMails) {
		this.outMails = outMails;
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
		if (!(object instanceof OutMailUserSeting)) {
			return false;
		}
		OutMailUserSeting rhs = (OutMailUserSeting) object;
		return new EqualsBuilder().append(this.id, rhs.id).append(this.userName, rhs.userName)
				.append(this.mailAddress, rhs.mailAddress).append(this.mailPass, rhs.mailPass)
				.append(this.smtpHost, rhs.smtpHost).append(this.smtpPort, rhs.smtpPort)
				.append(this.popHost, rhs.popHost).append(this.popPort, rhs.popPort).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id).append(this.userName)
				.append(this.mailAddress).append(this.mailPass).append(this.smtpHost).append(this.smtpPort)
				.append(this.popHost).append(this.popPort).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("userName", this.userName)
				.append("mailAddress", this.mailAddress).append("mailPass", this.mailPass)
				.append("smtpHost", this.smtpHost).append("smtpPort", this.smtpPort).append("popHost", this.popHost)
				.append("popPort", this.popPort).toString();
	}
}
