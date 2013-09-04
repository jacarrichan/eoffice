package com.palmelf.eoffice.dao.archive;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.archive.ArchRecUser;

import java.util.List;

public abstract interface ArchRecUserDao extends BaseDao<ArchRecUser> {
	public abstract List findDepAll();

	public abstract ArchRecUser getByDepId(Long paramLong);
}
