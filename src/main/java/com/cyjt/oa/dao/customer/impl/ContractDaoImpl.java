package com.cyjt.oa.dao.customer.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.customer.ContractDao;
import com.cyjt.oa.model.customer.Contract;

public class ContractDaoImpl extends BaseDaoImpl<Contract> implements
		ContractDao {
	public ContractDaoImpl() {
		super(Contract.class);
	}
}
