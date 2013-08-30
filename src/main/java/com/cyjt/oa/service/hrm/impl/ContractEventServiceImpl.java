package com.cyjt.oa.service.hrm.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.hrm.ContractEventDao;
import com.cyjt.oa.model.hrm.ContractEvent;
import com.cyjt.oa.service.hrm.ContractEventService;
import java.util.List;

public class ContractEventServiceImpl extends BaseServiceImpl<ContractEvent>
		implements ContractEventService {
	private ContractEventDao dao;

	public ContractEventServiceImpl(ContractEventDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<ContractEvent> findByContractId(Long contractId) {
		return this.dao.findByContractId(contractId);
	}
}
