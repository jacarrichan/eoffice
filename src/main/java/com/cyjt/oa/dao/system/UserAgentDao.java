package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.system.UserAgent;
import java.util.List;

public abstract interface UserAgentDao extends BaseDao<UserAgent> {
	public abstract List<UserAgent> getByUserId(Long paramLong);

	public abstract UserAgent getByUserIdGrantId(Long paramLong1,
			Long paramLong2);
}
