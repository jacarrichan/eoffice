package com.palmelf.eoffice.dao.hrm;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.hrm.ContractEvent;

import java.util.List;

public abstract interface ContractEventDao extends BaseDao<ContractEvent> {
	public abstract List<ContractEvent> findByContractId(Long paramLong);
}
