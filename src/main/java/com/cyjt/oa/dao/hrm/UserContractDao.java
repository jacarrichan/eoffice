package com.cyjt.oa.dao.hrm;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.hrm.UserContract;
import java.util.List;

public abstract interface UserContractDao extends BaseDao<UserContract> {
	public abstract boolean checkContractNo(String paramString);

	public abstract List<UserContract> findTime(Long paramLong);

	public abstract List<UserContract> findByExpireDate();
}
