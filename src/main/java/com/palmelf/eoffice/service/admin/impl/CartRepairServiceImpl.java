package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.CartRepairDao;
import com.palmelf.eoffice.model.admin.CartRepair;
import com.palmelf.eoffice.service.admin.CartRepairService;

public class CartRepairServiceImpl extends BaseServiceImpl<CartRepair>
		implements CartRepairService {
	private CartRepairDao dao;

	public CartRepairServiceImpl(CartRepairDao dao) {
		super(dao);
		this.dao = dao;
	}
}
