package com.palmelf.eoffice.service.personal.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.personal.HolidayRecordDao;
import com.palmelf.eoffice.model.personal.HolidayRecord;
import com.palmelf.eoffice.service.personal.HolidayRecordService;

public class HolidayRecordServiceImpl extends BaseServiceImpl<HolidayRecord>
		implements HolidayRecordService {
	private HolidayRecordDao dao;

	public HolidayRecordServiceImpl(HolidayRecordDao dao) {
		super(dao);
		this.dao = dao;
	}
}
