package com.cyjt.oa.service.customer.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.customer.ContractDao;
import com.cyjt.oa.model.customer.Contract;
import com.cyjt.oa.service.customer.ContractService;

public class ContractServiceImpl extends BaseServiceImpl<Contract> implements
		ContractService {
	private ContractDao dao;

	public ContractServiceImpl(ContractDao dao) {
		super(dao);
		this.dao = dao;
	}
}
