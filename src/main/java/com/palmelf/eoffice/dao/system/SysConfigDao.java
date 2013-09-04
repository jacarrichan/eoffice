package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.system.SysConfig;

import java.util.List;

public abstract interface SysConfigDao extends BaseDao<SysConfig> {
	public abstract SysConfig findByKey(String paramString);

	public abstract List<SysConfig> findConfigByTypeKey(String paramString);

	public abstract List findTypeKeys();
}
