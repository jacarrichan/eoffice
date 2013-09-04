package com.palmelf.eoffice.service.customer.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.customer.ContractDao;
import com.palmelf.eoffice.model.customer.Contract;
import com.palmelf.eoffice.service.customer.ContractService;

public class ContractServiceImpl extends BaseServiceImpl<Contract> implements
		ContractService {
	private ContractDao dao;

	public ContractServiceImpl(ContractDao dao) {
		super(dao);
		this.dao = dao;
	}
}
