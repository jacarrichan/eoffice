package com.palmelf.eoffice.dao.system.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.system.FunUrlDao;
import com.palmelf.eoffice.model.system.FunUrl;

public class FunUrlDaoImpl extends BaseDaoImpl<FunUrl> implements FunUrlDao {
	public FunUrlDaoImpl() {
		super(FunUrl.class);
	}

	public FunUrl getByPathFunId(String path, Long funId) {
		String hql = "from FunUrl fu where fu.urlPath=? and fu.appFunction.functionId=? ";
		return (FunUrl) findUnique(hql, new Object[] { path, funId });
	}
}
