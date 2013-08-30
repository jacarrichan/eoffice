package com.cyjt.oa.service.personal.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.personal.HolidayRecordDao;
import com.cyjt.oa.model.personal.HolidayRecord;
import com.cyjt.oa.service.personal.HolidayRecordService;

public class HolidayRecordServiceImpl extends BaseServiceImpl<HolidayRecord>
		implements HolidayRecordService {
	private HolidayRecordDao dao;

	public HolidayRecordServiceImpl(HolidayRecordDao dao) {
		super(dao);
		this.dao = dao;
	}
}
