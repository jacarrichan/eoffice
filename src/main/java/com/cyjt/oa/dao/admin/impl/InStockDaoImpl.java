package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.InStockDao;
import com.cyjt.oa.model.admin.InStock;

public class InStockDaoImpl extends BaseDaoImpl<InStock> implements InStockDao {
	public InStockDaoImpl() {
		super(InStock.class);
	}

	public Integer findInCountByBuyId(Long buyId) {
		String hql = "select vo.inCounts from InStock vo where vo.buyId=?";

		Object val = findUnique(hql, new Object[] { buyId });

		if (val == null) {
			return new Integer(0);
		}

		return new Integer(val.toString());
	}
}
