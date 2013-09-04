package com.palmelf.eoffice.dao.customer;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.customer.CusLinkman;

public abstract interface CusLinkmanDao extends BaseDao<CusLinkman> {
	public abstract boolean checkMainCusLinkman(Long paramLong1, Long paramLong2);
}
