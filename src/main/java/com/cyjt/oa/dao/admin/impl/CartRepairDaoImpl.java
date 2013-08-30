package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.CartRepairDao;
import com.cyjt.oa.model.admin.CartRepair;

public class CartRepairDaoImpl extends BaseDaoImpl<CartRepair> implements
		CartRepairDao {
	public CartRepairDaoImpl() {
		super(CartRepair.class);
	}
}
