package com.palmelf.eoffice.service.hrm.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.hrm.ContractEventDao;
import com.palmelf.eoffice.model.hrm.ContractEvent;
import com.palmelf.eoffice.service.hrm.ContractEventService;

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
