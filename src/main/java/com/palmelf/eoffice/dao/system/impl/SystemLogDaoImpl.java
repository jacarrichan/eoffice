package com.palmelf.eoffice.dao.system.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.system.SystemLogDao;
import com.palmelf.eoffice.model.system.SystemLog;

public class SystemLogDaoImpl extends BaseDaoImpl<SystemLog> implements
		SystemLogDao {
	public SystemLogDaoImpl() {
		super(SystemLog.class);
	}
}
