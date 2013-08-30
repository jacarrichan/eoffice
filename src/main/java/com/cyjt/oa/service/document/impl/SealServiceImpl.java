package com.cyjt.oa.service.document.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.document.SealDao;
import com.cyjt.oa.model.document.Seal;
import com.cyjt.oa.service.document.SealService;

public class SealServiceImpl extends BaseServiceImpl<Seal> implements
		SealService {
	private SealDao dao;

	public SealServiceImpl(SealDao dao) {
		super(dao);
		this.dao = dao;
	}
}
