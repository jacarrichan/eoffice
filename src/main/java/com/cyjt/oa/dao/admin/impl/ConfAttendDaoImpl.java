package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.ConfAttendDao;
import com.cyjt.oa.model.admin.ConfAttend;

public class ConfAttendDaoImpl extends BaseDaoImpl<ConfAttend> implements
		ConfAttendDao {
	public ConfAttendDaoImpl() {
		super(ConfAttend.class);
	}
}
