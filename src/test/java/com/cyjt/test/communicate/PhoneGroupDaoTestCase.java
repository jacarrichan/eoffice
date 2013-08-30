package com.cyjt.test.communicate;

import com.cyjt.oa.dao.communicate.PhoneGroupDao;
import com.cyjt.oa.model.communicate.PhoneGroup;
import com.cyjt.test.BaseTestCase;
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
