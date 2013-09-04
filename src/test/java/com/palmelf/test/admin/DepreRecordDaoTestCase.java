package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.DepreRecordDao;
import com.palmelf.eoffice.model.admin.DepreRecord;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class DepreRecordDaoTestCase extends BaseTestCase {

	@Resource
	private DepreRecordDao depreRecordDao;

	@Test
	@Rollback(false)
	public void add() {
		DepreRecord depreRecord = new DepreRecord();

		this.depreRecordDao.save(depreRecord);
	}
}
