package com.cyjt.oa.service.customer.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.customer.ContractConfigDao;
import com.cyjt.oa.model.customer.ContractConfig;
import com.cyjt.oa.service.customer.ContractConfigService;

public class ContractConfigServiceImpl extends BaseServiceImpl<ContractConfig>
		implements ContractConfigService {
	private ContractConfigDao dao;

	public ContractConfigServiceImpl(ContractConfigDao dao) {
		super(dao);
		this.dao = dao;
	}
}
