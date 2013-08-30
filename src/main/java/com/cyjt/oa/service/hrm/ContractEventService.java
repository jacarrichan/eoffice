package com.cyjt.oa.service.hrm;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.hrm.ContractEvent;
import java.util.List;

public abstract interface ContractEventService extends
		BaseService<ContractEvent> {
	public abstract List<ContractEvent> findByContractId(Long paramLong);
}
