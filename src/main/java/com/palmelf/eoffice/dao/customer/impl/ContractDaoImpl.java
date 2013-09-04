package com.palmelf.eoffice.dao.customer.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.customer.ContractDao;
import com.palmelf.eoffice.model.customer.Contract;

public class ContractDaoImpl extends BaseDaoImpl<Contract> implements
		ContractDao {
	public ContractDaoImpl() {
		super(Contract.class);
	}
}
