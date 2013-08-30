package com.cyjt.oa.dao.system.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.system.SystemLogDao;
import com.cyjt.oa.model.system.SystemLog;

public class SystemLogDaoImpl extends BaseDaoImpl<SystemLog> implements
		SystemLogDao {
	public SystemLogDaoImpl() {
		super(SystemLog.class);
	}
}
