package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.system.FunUrl;

public abstract interface FunUrlService extends BaseService<FunUrl> {
	public abstract FunUrl getByPathFunId(String paramString, Long paramLong);
}
