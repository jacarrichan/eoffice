package com.cyjt.oa.dao.hrm;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.hrm.ContractEvent;
import java.util.List;

public abstract interface ContractEventDao extends BaseDao<ContractEvent> {
	public abstract List<ContractEvent> findByContractId(Long paramLong);
}
