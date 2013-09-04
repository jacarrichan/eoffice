package com.palmelf.eoffice.model.system;

import com.google.gson.annotations.Expose;
import com.palmelf.core.model.BaseModel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jbpm.api.identity.Group;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "app_role")
public class AppRole extends BaseModel implements GrantedAuthority, Group {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String ROLE_PUBLIC = "ROLE_PUBLIC";

	public static String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

	public static final Long SUPER_ROLEID = Long.valueOf(-1L);
	public static final String SUPER_RIGHTS = "__ALL";

	@Expose
	private Long roleId;

	@Expose
	private String roleName;

	@Expose
	private String roleDesc;

	@Expose
	private Short status;

	@Expose
	private Short isDefaultIn;

	@Expose
	private String rights;
	private Set<AppFunction> functions = new HashSet<AppFunction>();
	private Set<AppUser> appUsers = new HashSet<AppUser>();

	@Column(name = "isDefaultIn", nullable = false)
	public Short getIsDefaultIn() {
		return this.isDefaultIn;
	}

	public void setIsDefaultIn(Short isDefaultIn) {
		this.isDefaultIn = isDefaultIn;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "roleId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "userId", nullable = false, updatable = false) })
	public Set<AppUser> getAppUsers() {
		return this.appUsers;
	}

	public void setAppUsers(Set<AppUser> appUsers) {
		this.appUsers = appUsers;
	}
	@Lob
	@Column(name = "rights")
	public String getRights() {
		return this.rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	@Id
	@GeneratedValue
	@Column(name = "roleId", unique = true, nullable = false)
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "roleName", nullable = false)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "roleDesc")
	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Transient
	public String getAuthority() {
		return this.roleName;
	}

	@Transient
	public String getId() {
		return this.roleId.toString();
	}

	@Transient
	public String getName() {
		return this.roleName;
	}

	@Transient
	public String getType() {
		return "candidate";
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "role_fun", joinColumns = { @JoinColumn(name = "roleId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "functionId", nullable = false, updatable = false) })
	public Set<AppFunction> getFunctions() {
		return this.functions;
	}

	public void setFunctions(Set<AppFunction> functions) {
		this.functions = functions;
	}
}
