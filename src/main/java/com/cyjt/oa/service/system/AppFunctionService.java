package com.cyjt.oa.service.system;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.system.AppFunction;

public abstract interface AppFunctionService extends BaseService<AppFunction> {
	public abstract AppFunction getByKey(String paramString);
}
