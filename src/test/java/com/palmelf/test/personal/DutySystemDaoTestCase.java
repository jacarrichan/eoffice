package com.palmelf.test.personal;

import com.palmelf.eoffice.dao.personal.DutySystemDao;
import com.palmelf.eoffice.model.personal.DutySystem;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class DutySystemDaoTestCase extends BaseTestCase {

	@Resource
	private DutySystemDao dutySystemDao;

	@Test
	@Rollback(false)
	public void add() {
		DutySystem dutySystem = new DutySystem();

		this.dutySystemDao.save(dutySystem);
	}
}
