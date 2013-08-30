package com.cyjt.oa.dao.admin;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.admin.ConfPrivilege;

public abstract interface ConfPrivilegeDao extends BaseDao<ConfPrivilege> {
	public abstract Short getPrivilege(Long paramLong1, Long paramLong2,
			Short paramShort);

	public abstract void delete(Long paramLong);
}
