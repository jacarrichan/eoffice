package com.palmelf.eoffice.service.hrm;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.hrm.UserContract;

import java.util.List;

public abstract interface UserContractService extends BaseService<UserContract> {
	public abstract boolean checkContractNo(String paramString);

	public abstract List<UserContract> findTime(Long paramLong);

	public abstract void sendContractTime();
}
