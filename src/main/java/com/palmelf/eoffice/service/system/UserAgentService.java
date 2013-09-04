package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.system.UserAgent;

import java.util.List;

public abstract interface UserAgentService extends BaseService<UserAgent> {
	public abstract List<UserAgent> getByUserId(Long paramLong);

	public abstract UserAgent getByUserIdGrantId(Long paramLong1,
			Long paramLong2);

	public abstract void delUserGrants(Long paramLong);
}
