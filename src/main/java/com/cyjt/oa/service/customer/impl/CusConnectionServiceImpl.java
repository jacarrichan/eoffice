package com.cyjt.oa.service.customer.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.customer.CusConnectionDao;
import com.cyjt.oa.model.customer.CusConnection;
import com.cyjt.oa.service.customer.CusConnectionService;

public class CusConnectionServiceImpl extends BaseServiceImpl<CusConnection>
		implements CusConnectionService {
	private CusConnectionDao dao;

	public CusConnectionServiceImpl(CusConnectionDao dao) {
		super(dao);
		this.dao = dao;
	}
}
