package com.cyjt.oa.dao.customer.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.customer.CusConnectionDao;
import com.cyjt.oa.model.customer.CusConnection;

public class CusConnectionDaoImpl extends BaseDaoImpl<CusConnection> implements
		CusConnectionDao {
	public CusConnectionDaoImpl() {
		super(CusConnection.class);
	}
}
