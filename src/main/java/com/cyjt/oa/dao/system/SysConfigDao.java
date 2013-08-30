package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.system.SysConfig;
import java.util.List;

public abstract interface SysConfigDao extends BaseDao<SysConfig> {
	public abstract SysConfig findByKey(String paramString);

	public abstract List<SysConfig> findConfigByTypeKey(String paramString);

	public abstract List findTypeKeys();
}
