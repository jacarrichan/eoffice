package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.ConfAttendDao;
import com.palmelf.eoffice.model.admin.ConfAttend;

public class ConfAttendDaoImpl extends BaseDaoImpl<ConfAttend> implements
		ConfAttendDao {
	public ConfAttendDaoImpl() {
		super(ConfAttend.class);
	}
}
