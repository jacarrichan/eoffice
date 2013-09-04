package com.palmelf.eoffice.service.customer;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.customer.CusLinkman;

public abstract interface CusLinkmanService extends BaseService<CusLinkman> {
	public abstract boolean checkMainCusLinkman(Long paramLong1, Long paramLong2);
}
