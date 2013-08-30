package com.cyjt.oa.dao.customer.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.customer.ProviderDao;
import com.cyjt.oa.model.customer.Provider;

public class ProviderDaoImpl extends BaseDaoImpl<Provider> implements
		ProviderDao {
	public ProviderDaoImpl() {
		super(Provider.class);
	}
}
