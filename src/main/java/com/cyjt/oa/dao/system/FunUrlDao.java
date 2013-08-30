package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.system.FunUrl;

public abstract interface FunUrlDao extends BaseDao<FunUrl> {
	public abstract FunUrl getByPathFunId(String paramString, Long paramLong);
}
