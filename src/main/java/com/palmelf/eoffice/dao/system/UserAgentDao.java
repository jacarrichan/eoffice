package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.system.UserAgent;

import java.util.List;

public abstract interface UserAgentDao extends BaseDao<UserAgent> {
	public abstract List<UserAgent> getByUserId(Long paramLong);

	public abstract UserAgent getByUserIdGrantId(Long paramLong1,
			Long paramLong2);
}
