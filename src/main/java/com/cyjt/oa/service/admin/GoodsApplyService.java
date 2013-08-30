package com.cyjt.oa.service.admin;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.admin.GoodsApply;

public abstract interface GoodsApplyService extends BaseService<GoodsApply> {
	public abstract Integer check(Long paramLong, Short paramShort);
}
