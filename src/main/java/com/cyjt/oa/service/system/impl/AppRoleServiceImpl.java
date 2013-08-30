package com.cyjt.oa.service.system.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.system.AppRoleDao;
import com.cyjt.oa.model.system.AppRole;
import com.cyjt.oa.service.system.AppRoleService;
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
