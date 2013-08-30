package com.cyjt.oa.dao.archive;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.archive.ArchRecUser;
import java.util.List;

public abstract interface ArchRecUserDao extends BaseDao<ArchRecUser> {
	public abstract List findDepAll();

	public abstract ArchRecUser getByDepId(Long paramLong);
}
