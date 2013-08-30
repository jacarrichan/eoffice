package com.cyjt.oa.service.admin;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.admin.InStock;

public abstract interface InStockService extends BaseService<InStock> {
	public abstract Integer findInCountByBuyId(Long paramLong);
}
