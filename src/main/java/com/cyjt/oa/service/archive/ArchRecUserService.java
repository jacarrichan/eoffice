package com.cyjt.oa.service.archive;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.archive.ArchRecUser;
import java.util.List;

public abstract interface ArchRecUserService extends BaseService<ArchRecUser> {
	public abstract List findDepAll();

	public abstract ArchRecUser getByDepId(Long paramLong);
}
