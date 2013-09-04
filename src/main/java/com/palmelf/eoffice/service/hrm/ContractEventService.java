package com.palmelf.eoffice.service.hrm;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.hrm.ContractEvent;

import java.util.List;

public abstract interface ContractEventService extends
		BaseService<ContractEvent> {
	public abstract List<ContractEvent> findByContractId(Long paramLong);
}
