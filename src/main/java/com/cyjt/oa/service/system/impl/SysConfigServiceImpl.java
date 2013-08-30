package com.cyjt.oa.service.system.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.system.SysConfigDao;
import com.cyjt.oa.model.system.SysConfig;
import com.cyjt.oa.service.system.SysConfigService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig> implements
		SysConfigService {
	private SysConfigDao dao;

	public SysConfigServiceImpl(SysConfigDao dao) {
		super(dao);
		this.dao = dao;
	}

	public SysConfig findByKey(String key) {
		return this.dao.findByKey(key);
	}

	public Map findByType() {
		List<String> list = this.dao.findTypeKeys();
		Map cList = new HashMap();
		for (String typeKey : list) {
			List confList = this.dao.findConfigByTypeKey(typeKey);
			cList.put(typeKey, confList);
		}
		return cList;
	}
}
