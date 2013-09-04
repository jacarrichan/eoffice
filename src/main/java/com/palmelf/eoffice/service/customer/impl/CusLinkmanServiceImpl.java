package com.palmelf.eoffice.service.customer.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.customer.CusLinkmanDao;
import com.palmelf.eoffice.model.customer.CusLinkman;
import com.palmelf.eoffice.service.customer.CusLinkmanService;

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
