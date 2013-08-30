package com.cyjt.test.personal;

import com.cyjt.oa.dao.personal.HolidayRecordDao;
import com.cyjt.oa.model.personal.HolidayRecord;
import com.cyjt.test.BaseTestCase;
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
