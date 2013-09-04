package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.system.FunUrlDao;
import com.palmelf.eoffice.model.system.FunUrl;
import com.palmelf.eoffice.service.system.FunUrlService;

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
