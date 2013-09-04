package com.palmelf.eoffice.dao.customer.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.customer.CusConnectionDao;
import com.palmelf.eoffice.model.customer.CusConnection;

public class CusConnectionDaoImpl extends BaseDaoImpl<CusConnection> implements
		CusConnectionDao {
	public CusConnectionDaoImpl() {
		super(CusConnection.class);
	}
}
