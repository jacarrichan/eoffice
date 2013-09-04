package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.CartRepairDao;
import com.palmelf.eoffice.model.admin.CartRepair;

public class CartRepairDaoImpl extends BaseDaoImpl<CartRepair> implements
		CartRepairDao {
	public CartRepairDaoImpl() {
		super(CartRepair.class);
	}
}
