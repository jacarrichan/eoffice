package com.cyjt.oa.dao.customer;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.customer.CusLinkman;

public abstract interface CusLinkmanDao extends BaseDao<CusLinkman> {
	public abstract boolean checkMainCusLinkman(Long paramLong1, Long paramLong2);
}
