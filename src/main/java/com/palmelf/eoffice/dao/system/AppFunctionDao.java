package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.system.AppFunction;

public abstract interface AppFunctionDao extends BaseDao<AppFunction> {
	public abstract AppFunction getByKey(String paramString);
}
