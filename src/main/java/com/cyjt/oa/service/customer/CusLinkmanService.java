package com.cyjt.oa.service.customer;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.customer.CusLinkman;

public abstract interface CusLinkmanService extends BaseService<CusLinkman> {
	public abstract boolean checkMainCusLinkman(Long paramLong1, Long paramLong2);
}
