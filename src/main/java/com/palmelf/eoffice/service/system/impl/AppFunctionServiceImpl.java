package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.system.AppFunctionDao;
import com.palmelf.eoffice.model.system.AppFunction;
import com.palmelf.eoffice.service.system.AppFunctionService;

public class AppFunctionServiceImpl extends BaseServiceImpl<AppFunction>
		implements AppFunctionService {
	private AppFunctionDao dao;

	public AppFunctionServiceImpl(AppFunctionDao dao) {
		super(dao);
		this.dao = dao;
	}

	public AppFunction getByKey(String key) {
		return this.dao.getByKey(key);
	}
}
