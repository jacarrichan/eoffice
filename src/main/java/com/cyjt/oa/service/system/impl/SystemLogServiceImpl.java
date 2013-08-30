package com.cyjt.oa.service.system.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.system.SystemLogDao;
import com.cyjt.oa.model.system.SystemLog;
import com.cyjt.oa.service.system.SystemLogService;

public class SystemLogServiceImpl extends BaseServiceImpl<SystemLog> implements
		SystemLogService {
	private SystemLogDao dao;

	public SystemLogServiceImpl(SystemLogDao dao) {
		super(dao);
		this.dao = dao;
	}
}
