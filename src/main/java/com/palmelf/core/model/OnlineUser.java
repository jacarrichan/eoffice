package com.palmelf.core.model;

public class OnlineUser {
	private String sessionId;
	private Long userId;
	private String username;
	private String fullname;
	private String depPath;
	private String roleIds;
	private Short title;

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDepPath() {
		return this.depPath;
	}

	public void setDepPath(String depPath) {
		this.depPath = depPath;
	}

	public String getRoleIds() {
		return this.roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Short getTitle() {
		return this.title;
	}

	public void setTitle(Short title) {
		this.title = title;
	}
}
