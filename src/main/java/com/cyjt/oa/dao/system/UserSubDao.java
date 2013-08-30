package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.system.UserSub;
import java.util.List;

public abstract interface UserSubDao extends BaseDao<UserSub> {
	public abstract List<Long> upUser(Long paramLong);

	public abstract List<Long> subUsers(Long paramLong);

	public abstract List<UserSub> findByUser(Long paramLong);
}
