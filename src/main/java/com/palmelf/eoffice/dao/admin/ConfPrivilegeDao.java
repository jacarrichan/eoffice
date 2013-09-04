package com.palmelf.eoffice.dao.admin;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.admin.ConfPrivilege;

public abstract interface ConfPrivilegeDao extends BaseDao<ConfPrivilege> {
	public abstract Short getPrivilege(Long paramLong1, Long paramLong2,
			Short paramShort);

	public abstract void delete(Long paramLong);
}
