package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.DepreRecordDao;
import com.cyjt.oa.model.admin.DepreRecord;
import com.cyjt.oa.service.admin.DepreRecordService;
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
