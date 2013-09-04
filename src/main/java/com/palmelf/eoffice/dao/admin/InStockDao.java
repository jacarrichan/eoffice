package com.palmelf.eoffice.dao.admin;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.admin.InStock;

public abstract interface InStockDao extends BaseDao<InStock> {
	public abstract Integer findInCountByBuyId(Long paramLong);
}
