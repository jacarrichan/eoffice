package com.cyjt.oa.service.admin;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.admin.ConfPrivilege;

public abstract interface ConfPrivilegeService extends
		BaseService<ConfPrivilege> {
	public abstract Short getPrivilege(Long paramLong, Short paramShort);
}
