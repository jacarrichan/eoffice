package com.palmelf.eoffice.service.customer.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.customer.ContractConfigDao;
import com.palmelf.eoffice.model.customer.ContractConfig;
import com.palmelf.eoffice.service.customer.ContractConfigService;

public class ContractConfigServiceImpl extends BaseServiceImpl<ContractConfig>
		implements ContractConfigService {
	private ContractConfigDao dao;

	public ContractConfigServiceImpl(ContractConfigDao dao) {
		super(dao);
		this.dao = dao;
	}
}
