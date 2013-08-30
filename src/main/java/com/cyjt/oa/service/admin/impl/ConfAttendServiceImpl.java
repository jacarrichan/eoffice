package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.ConfAttendDao;
import com.cyjt.oa.model.admin.ConfAttend;
import com.cyjt.oa.service.admin.ConfAttendService;

public class ConfAttendServiceImpl extends BaseServiceImpl<ConfAttend>
		implements ConfAttendService {
	private ConfAttendDao dao;

	public ConfAttendServiceImpl(ConfAttendDao dao) {
		super(dao);
		this.dao = dao;
	}
}
