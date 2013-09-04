package com.palmelf.eoffice.service.archive;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.archive.ArchRecUser;

import java.util.List;

public abstract interface ArchRecUserService extends BaseService<ArchRecUser> {
	public abstract List findDepAll();

	public abstract ArchRecUser getByDepId(Long paramLong);
}
