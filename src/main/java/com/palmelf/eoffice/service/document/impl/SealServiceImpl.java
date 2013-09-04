package com.palmelf.eoffice.service.document.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.document.SealDao;
import com.palmelf.eoffice.model.document.Seal;
import com.palmelf.eoffice.service.document.SealService;

public class SealServiceImpl extends BaseServiceImpl<Seal> implements
		SealService {
	private SealDao dao;

	public SealServiceImpl(SealDao dao) {
		super(dao);
		this.dao = dao;
	}
}
