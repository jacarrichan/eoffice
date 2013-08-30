package com.cyjt.oa.service.system;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.system.SysConfig;
import java.util.Map;

public abstract interface SysConfigService extends BaseService<SysConfig> {
	public abstract SysConfig findByKey(String paramString);

	public abstract Map findByType();
}
