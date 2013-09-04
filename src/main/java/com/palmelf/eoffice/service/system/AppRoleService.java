package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.system.AppRole;

import java.util.HashMap;
import java.util.Set;

public abstract interface AppRoleService extends BaseService<AppRole> {
	public abstract AppRole getByRoleName(String paramString);

	public abstract HashMap<String, Set<String>> getSecurityDataSource();
}
