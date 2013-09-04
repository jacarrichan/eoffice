package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.system.FunUrl;

public abstract interface FunUrlDao extends BaseDao<FunUrl> {
	public abstract FunUrl getByPathFunId(String paramString, Long paramLong);
}
