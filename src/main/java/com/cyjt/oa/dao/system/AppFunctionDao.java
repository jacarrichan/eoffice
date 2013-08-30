package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.system.AppFunction;

public abstract interface AppFunctionDao extends BaseDao<AppFunction> {
	public abstract AppFunction getByKey(String paramString);
}
