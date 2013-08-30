package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.CartRepairDao;
import com.cyjt.oa.model.admin.CartRepair;
import com.cyjt.oa.service.admin.CartRepairService;

public class CartRepairServiceImpl extends BaseServiceImpl<CartRepair>
		implements CartRepairService {
	private CartRepairDao dao;

	public CartRepairServiceImpl(CartRepairDao dao) {
		super(dao);
		this.dao = dao;
	}
}
