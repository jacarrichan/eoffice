package com.palmelf.core.security;

import com.palmelf.eoffice.model.system.AppRole;
import com.palmelf.eoffice.service.system.AppRoleService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SecurityDataSource {
	private AppRoleService appRoleService;
	private HashSet<String> anonymousUrls = null;

	private HashSet<String> publicUrls = null;

	public void setAppRoleService(AppRoleService appRoleService) {
		this.appRoleService = appRoleService;
	}

	public Set<String> getAnonymousUrls() {
		return this.anonymousUrls;
	}

	public void setAnonymousUrls(Set<String> anonymousUrls) {
		this.anonymousUrls = ((HashSet<String>) anonymousUrls);
	}

	public HashSet<String> getPublicUrls() {
		return this.publicUrls;
	}

	public void setPublicUrls(HashSet<String> publicUrls) {
		this.publicUrls = publicUrls;
	}

	public HashMap<String, Set<String>> getDataSource() {
		HashMap<String, Set<String>> tmap = this.appRoleService.getSecurityDataSource();
		tmap.put(AppRole.ROLE_ANONYMOUS, this.anonymousUrls);
		tmap.put(AppRole.ROLE_PUBLIC, this.publicUrls);
		return tmap;
	}
}
