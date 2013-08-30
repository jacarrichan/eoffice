package com.cyjt.oa.service.customer.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.customer.ProviderDao;
import com.cyjt.oa.model.customer.Provider;
import com.cyjt.oa.service.customer.ProviderService;

public class ProviderServiceImpl extends BaseServiceImpl<Provider> implements
		ProviderService {
	private ProviderDao dao;

	public ProviderServiceImpl(ProviderDao dao) {
		super(dao);
		this.dao = dao;
	}
}
