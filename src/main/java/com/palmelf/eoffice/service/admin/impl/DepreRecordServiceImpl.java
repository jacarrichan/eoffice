package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.DepreRecordDao;
import com.palmelf.eoffice.model.admin.DepreRecord;
import com.palmelf.eoffice.service.admin.DepreRecordService;

import java.util.Date;

public class DepreRecordServiceImpl extends BaseServiceImpl<DepreRecord>
		implements DepreRecordService {
	private DepreRecordDao dao;

	public DepreRecordServiceImpl(DepreRecordDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Date findMaxDate(Long assetsId) {
		return this.dao.findMaxDate(assetsId);
	}
}
