package com.palmelf.eoffice.dao.personal.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.personal.HolidayRecordDao;
import com.palmelf.eoffice.model.personal.HolidayRecord;

public class HolidayRecordDaoImpl extends BaseDaoImpl<HolidayRecord> implements
		HolidayRecordDao {
	public HolidayRecordDaoImpl() {
		super(HolidayRecord.class);
	}
}
