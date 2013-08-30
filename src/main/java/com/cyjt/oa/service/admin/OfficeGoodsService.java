package com.cyjt.oa.service.admin;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.admin.OfficeGoods;

public abstract interface OfficeGoodsService extends BaseService<OfficeGoods> {
	public abstract void sendWarmMessage();
}
