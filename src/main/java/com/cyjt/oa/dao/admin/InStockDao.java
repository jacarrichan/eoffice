package com.cyjt.oa.dao.admin;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.admin.InStock;

public abstract interface InStockDao extends BaseDao<InStock> {
	public abstract Integer findInCountByBuyId(Long paramLong);
}
