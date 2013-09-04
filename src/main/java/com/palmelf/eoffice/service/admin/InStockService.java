package com.palmelf.eoffice.service.admin;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.admin.InStock;

public abstract interface InStockService extends BaseService<InStock> {
	public abstract Integer findInCountByBuyId(Long paramLong);
}
