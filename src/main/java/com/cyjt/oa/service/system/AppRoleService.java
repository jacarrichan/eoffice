package com.cyjt.oa.service.system;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.system.AppRole;
import java.util.HashMap;
import java.util.Set;

public abstract interface AppRoleService extends BaseService<AppRole> {
	public abstract AppRole getByRoleName(String paramString);

	public abstract HashMap<String, Set<String>> getSecurityDataSource();
}
