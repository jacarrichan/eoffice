package com.cyjt.test.personal;

import com.cyjt.oa.dao.personal.DutySystemDao;
import com.cyjt.oa.model.personal.DutySystem;
import com.cyjt.test.BaseTestCase;
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
