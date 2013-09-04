package com.palmelf.eoffice.service.admin;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.admin.ConfPrivilege;

public abstract interface ConfPrivilegeService extends
		BaseService<ConfPrivilege> {
	public abstract Short getPrivilege(Long paramLong, Short paramShort);
}
