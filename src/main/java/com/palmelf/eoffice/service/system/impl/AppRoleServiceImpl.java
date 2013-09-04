package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.system.AppRoleDao;
import com.palmelf.eoffice.model.system.AppRole;
import com.palmelf.eoffice.service.system.AppRoleService;

import java.util.HashMap;
import java.util.Set;

public class AppRoleServiceImpl extends BaseServiceImpl<AppRole> implements
		AppRoleService {
	private AppRoleDao dao;

	public AppRoleServiceImpl(AppRoleDao dao) {
		super(dao);
		this.dao = dao;
	}

	public AppRole getByRoleName(String roleName) {
		return this.dao.getByRoleName(roleName);
	}

	public HashMap<String, Set<String>> getSecurityDataSource() {
		return this.dao.getSecurityDataSource();
	}
}
