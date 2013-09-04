package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.system.UserSub;

import java.util.List;
import java.util.Set;

public abstract interface UserSubService extends BaseService<UserSub> {
	public abstract Set<Long> findAllUpUser(Long paramLong);

	public abstract List<Long> subUsers(Long paramLong);

	public abstract List<Long> upUser(Long paramLong);

	public abstract List<UserSub> findByUser(Long paramLong);

	public abstract void delSubUser(Long paramLong);
}
