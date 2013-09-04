package com.palmelf.test.personal;

import com.palmelf.eoffice.dao.personal.DutyDao;
import com.palmelf.eoffice.model.personal.Duty;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class DutyDaoTestCase extends BaseTestCase {

	@Resource
	private DutyDao dutyDao;

	@Test
	@Rollback(false)
	public void add() {
		Duty duty = new Duty();

		this.dutyDao.save(duty);
	}
}
