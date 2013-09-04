package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.system.UserSub;

import java.util.List;

public abstract interface UserSubDao extends BaseDao<UserSub> {
	public abstract List<Long> upUser(Long paramLong);

	public abstract List<Long> subUsers(Long paramLong);

	public abstract List<UserSub> findByUser(Long paramLong);
}
