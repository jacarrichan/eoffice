package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.ConfAttendDao;
import com.palmelf.eoffice.model.admin.ConfAttend;
import com.palmelf.test.BaseTestCase;

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
