package com.cyjt.oa.service.hrm;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.hrm.UserContract;
import java.util.List;

public abstract interface UserContractService extends BaseService<UserContract> {
	public abstract boolean checkContractNo(String paramString);

	public abstract List<UserContract> findTime(Long paramLong);

	public abstract void sendContractTime();
}
