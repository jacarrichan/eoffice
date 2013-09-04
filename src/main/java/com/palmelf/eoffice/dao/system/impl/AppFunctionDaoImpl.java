package com.palmelf.eoffice.dao.system.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.system.AppFunctionDao;
import com.palmelf.eoffice.model.system.AppFunction;

public class AppFunctionDaoImpl extends BaseDaoImpl<AppFunction> implements
		AppFunctionDao {
	public AppFunctionDaoImpl() {
		super(AppFunction.class);
	}

	public AppFunction getByKey(String key) {
		String hql = "from AppFunction af where af.funKey=?";
		return (AppFunction) findUnique(hql, new String[] { key });
	}
}
