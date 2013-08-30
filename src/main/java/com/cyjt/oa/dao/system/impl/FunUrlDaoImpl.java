package com.cyjt.oa.dao.system.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.system.FunUrlDao;
import com.cyjt.oa.model.system.FunUrl;

public class FunUrlDaoImpl extends BaseDaoImpl<FunUrl> implements FunUrlDao {
	public FunUrlDaoImpl() {
		super(FunUrl.class);
	}

	public FunUrl getByPathFunId(String path, Long funId) {
		String hql = "from FunUrl fu where fu.urlPath=? and fu.appFunction.functionId=? ";
		return (FunUrl) findUnique(hql, new Object[] { path, funId });
	}
}
