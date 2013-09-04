package com.palmelf.eoffice.service.customer.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.customer.CusConnectionDao;
import com.palmelf.eoffice.model.customer.CusConnection;
import com.palmelf.eoffice.service.customer.CusConnectionService;

public class CusConnectionServiceImpl extends BaseServiceImpl<CusConnection>
		implements CusConnectionService {
	private CusConnectionDao dao;

	public CusConnectionServiceImpl(CusConnectionDao dao) {
		super(dao);
		this.dao = dao;
	}
}
