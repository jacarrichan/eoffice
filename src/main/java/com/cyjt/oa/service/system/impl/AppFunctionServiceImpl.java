package com.cyjt.oa.service.system.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.system.AppFunctionDao;
import com.cyjt.oa.model.system.AppFunction;
import com.cyjt.oa.service.system.AppFunctionService;

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
