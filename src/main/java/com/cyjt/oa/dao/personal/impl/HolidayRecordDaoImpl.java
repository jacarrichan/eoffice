package com.cyjt.oa.dao.personal.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.personal.HolidayRecordDao;
import com.cyjt.oa.model.personal.HolidayRecord;

public class HolidayRecordDaoImpl extends BaseDaoImpl<HolidayRecord> implements
		HolidayRecordDao {
	public HolidayRecordDaoImpl() {
		super(HolidayRecord.class);
	}
}
