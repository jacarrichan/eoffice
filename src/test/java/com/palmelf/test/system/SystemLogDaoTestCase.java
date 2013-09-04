package com.palmelf.test.system;

import com.palmelf.eoffice.dao.system.SystemLogDao;
import com.palmelf.eoffice.model.system.SystemLog;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class SystemLogDaoTestCase extends BaseTestCase {

	@Resource
	private SystemLogDao systemLogDao;

	@Test
	@Rollback(false)
	public void add() {
		SystemLog systemLog = new SystemLog();

		this.systemLogDao.save(systemLog);
	}
}
