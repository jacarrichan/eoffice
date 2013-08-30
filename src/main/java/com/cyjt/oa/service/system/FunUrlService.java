package com.cyjt.oa.service.system;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.system.FunUrl;

public abstract interface FunUrlService extends BaseService<FunUrl> {
	public abstract FunUrl getByPathFunId(String paramString, Long paramLong);
}
