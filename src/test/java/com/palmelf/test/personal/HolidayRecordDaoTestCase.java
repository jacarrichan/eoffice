package com.palmelf.test.personal;

import com.palmelf.eoffice.dao.personal.HolidayRecordDao;
import com.palmelf.eoffice.model.personal.HolidayRecord;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class HolidayRecordDaoTestCase extends BaseTestCase {

	@Resource
	private HolidayRecordDao holidayRecordDao;

	@Test
	@Rollback(false)
	public void add() {
		HolidayRecord holidayRecord = new HolidayRecord();

		this.holidayRecordDao.save(holidayRecord);
	}
}
