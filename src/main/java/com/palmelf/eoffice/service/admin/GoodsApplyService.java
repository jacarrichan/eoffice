package com.palmelf.eoffice.service.admin;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.admin.GoodsApply;

public abstract interface GoodsApplyService extends BaseService<GoodsApply> {
	public abstract Integer check(Long paramLong, Short paramShort);
}
