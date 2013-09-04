package com.palmelf.eoffice.dao.customer.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.customer.ProviderDao;
import com.palmelf.eoffice.model.customer.Provider;

public class ProviderDaoImpl extends BaseDaoImpl<Provider> implements
		ProviderDao {
	public ProviderDaoImpl() {
		super(Provider.class);
	}
}
