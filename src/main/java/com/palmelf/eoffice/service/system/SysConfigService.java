package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.system.SysConfig;

import java.util.Map;

public abstract interface SysConfigService extends BaseService<SysConfig> {
	public abstract SysConfig findByKey(String paramString);

	public abstract Map findByType();
}
