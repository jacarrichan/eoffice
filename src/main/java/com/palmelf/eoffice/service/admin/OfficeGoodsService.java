package com.palmelf.eoffice.service.admin;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.admin.OfficeGoods;

public abstract interface OfficeGoodsService extends BaseService<OfficeGoods> {
	public abstract void sendWarmMessage();
}
