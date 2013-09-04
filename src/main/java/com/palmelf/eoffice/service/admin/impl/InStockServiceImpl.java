package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.InStockDao;
import com.palmelf.eoffice.model.admin.InStock;
import com.palmelf.eoffice.service.admin.InStockService;

public class InStockServiceImpl extends BaseServiceImpl<InStock> implements
		InStockService {
	private InStockDao dao;

	public InStockServiceImpl(InStockDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Integer findInCountByBuyId(Long buyId) {
		return this.dao.findInCountByBuyId(buyId);
	}
}
