package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.InStockDao;
import com.cyjt.oa.model.admin.InStock;
import com.cyjt.oa.service.admin.InStockService;

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
