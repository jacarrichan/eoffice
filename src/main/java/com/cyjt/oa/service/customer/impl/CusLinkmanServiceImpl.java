package com.cyjt.oa.service.customer.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.customer.CusLinkmanDao;
import com.cyjt.oa.model.customer.CusLinkman;
import com.cyjt.oa.service.customer.CusLinkmanService;

public class CusLinkmanServiceImpl extends BaseServiceImpl<CusLinkman>
		implements CusLinkmanService {
	private CusLinkmanDao dao;

	public CusLinkmanServiceImpl(CusLinkmanDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean checkMainCusLinkman(Long customerId, Long linkmanId) {
		return this.dao.checkMainCusLinkman(customerId, linkmanId);
	}
}
