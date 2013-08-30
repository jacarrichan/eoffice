package com.cyjt.oa.dao.customer.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.customer.ContractConfigDao;
import com.cyjt.oa.model.customer.ContractConfig;

public class ContractConfigDaoImpl extends BaseDaoImpl<ContractConfig>
		implements ContractConfigDao {
	public ContractConfigDaoImpl() {
		super(ContractConfig.class);
	}
}
