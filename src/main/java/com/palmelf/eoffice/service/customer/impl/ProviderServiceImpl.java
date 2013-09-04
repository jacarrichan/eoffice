package com.palmelf.eoffice.service.customer.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.customer.ProviderDao;
import com.palmelf.eoffice.model.customer.Provider;
import com.palmelf.eoffice.service.customer.ProviderService;

public class ProviderServiceImpl extends BaseServiceImpl<Provider> implements
		ProviderService {
	private ProviderDao dao;

	public ProviderServiceImpl(ProviderDao dao) {
		super(dao);
		this.dao = dao;
	}
}
