package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.system.SystemLogDao;
import com.palmelf.eoffice.model.system.SystemLog;
import com.palmelf.eoffice.service.system.SystemLogService;

public class SystemLogServiceImpl extends BaseServiceImpl<SystemLog> implements
		SystemLogService {
	private SystemLogDao dao;

	public SystemLogServiceImpl(SystemLogDao dao) {
		super(dao);
		this.dao = dao;
	}
}
