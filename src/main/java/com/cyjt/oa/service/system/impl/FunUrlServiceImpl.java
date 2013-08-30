package com.cyjt.oa.service.system.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.system.FunUrlDao;
import com.cyjt.oa.model.system.FunUrl;
import com.cyjt.oa.service.system.FunUrlService;

public class FunUrlServiceImpl extends BaseServiceImpl<FunUrl> implements
		FunUrlService {
	private FunUrlDao dao;

	public FunUrlServiceImpl(FunUrlDao dao) {
		super(dao);
		this.dao = dao;
	}

	public FunUrl getByPathFunId(String path, Long funId) {
		return this.dao.getByPathFunId(path, funId);
	}
}
