package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.system.AppFunction;

public abstract interface AppFunctionService extends BaseService<AppFunction> {
	public abstract AppFunction getByKey(String paramString);
}
