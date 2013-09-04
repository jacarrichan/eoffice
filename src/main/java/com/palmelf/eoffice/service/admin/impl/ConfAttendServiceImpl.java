package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.ConfAttendDao;
import com.palmelf.eoffice.model.admin.ConfAttend;
import com.palmelf.eoffice.service.admin.ConfAttendService;

public class ConfAttendServiceImpl extends BaseServiceImpl<ConfAttend>
		implements ConfAttendService {
	private ConfAttendDao dao;

	public ConfAttendServiceImpl(ConfAttendDao dao) {
		super(dao);
		this.dao = dao;
	}
}
