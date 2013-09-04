package com.palmelf.test.personal;

import com.palmelf.eoffice.dao.personal.DutyRegisterDao;
import com.palmelf.eoffice.model.personal.DutyRegister;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class DutyRegisterDaoTestCase extends BaseTestCase {

	@Resource
	private DutyRegisterDao dutyRegisterDao;

	@Test
	@Rollback(false)
	public void add() {
		DutyRegister dutyRegister = new DutyRegister();

		this.dutyRegisterDao.save(dutyRegister);
	}
}
