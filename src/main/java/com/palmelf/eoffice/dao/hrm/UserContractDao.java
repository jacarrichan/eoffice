package com.palmelf.eoffice.dao.hrm;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.hrm.UserContract;

import java.util.List;

public abstract interface UserContractDao extends BaseDao<UserContract> {
	public abstract boolean checkContractNo(String paramString);

	public abstract List<UserContract> findTime(Long paramLong);

	public abstract List<UserContract> findByExpireDate();
}
