package com.palmelf.eoffice.dao.customer.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.customer.ContractConfigDao;
import com.palmelf.eoffice.model.customer.ContractConfig;

public class ContractConfigDaoImpl extends BaseDaoImpl<ContractConfig>
		implements ContractConfigDao {
	public ContractConfigDaoImpl() {
		super(ContractConfig.class);
	}
}
