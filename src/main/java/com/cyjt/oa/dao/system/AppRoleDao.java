package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.system.AppRole;
import java.util.HashMap;
import java.util.Set;

public abstract interface AppRoleDao extends BaseDao<AppRole> {
	public abstract AppRole getByRoleName(String paramString);

	public abstract HashMap<String, Set<String>> getSecurityDataSource();
}
