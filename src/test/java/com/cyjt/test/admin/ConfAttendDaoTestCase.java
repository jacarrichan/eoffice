package com.cyjt.test.admin;

import com.cyjt.oa.dao.admin.ConfAttendDao;
import com.cyjt.oa.model.admin.ConfAttend;
import com.cyjt.test.BaseTestCase;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ConfAttendDaoTestCase extends BaseTestCase {

	@Resource
	private ConfAttendDao confAttendDao;

	@Test
	@Rollback(false)
	public void add() {
		ConfAttend confAttend = new ConfAttend();

		this.confAttendDao.save(confAttend);
	}
}
