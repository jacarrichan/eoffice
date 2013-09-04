package com.palmelf.test.communicate;

import com.palmelf.eoffice.dao.communicate.PhoneGroupDao;
import com.palmelf.eoffice.model.communicate.PhoneGroup;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class PhoneGroupDaoTestCase extends BaseTestCase {

	@Resource
	private PhoneGroupDao phoneGroupDao;

	@Test
	@Rollback(false)
	public void add() {
		PhoneGroup phoneGroup = new PhoneGroup();

		this.phoneGroupDao.save(phoneGroup);
	}
}
