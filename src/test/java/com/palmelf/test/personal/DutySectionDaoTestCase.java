package com.palmelf.test.personal;

import com.palmelf.eoffice.dao.personal.DutySectionDao;
import com.palmelf.eoffice.model.personal.DutySection;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class DutySectionDaoTestCase extends BaseTestCase {

	@Resource
	private DutySectionDao dutySectionDao;

	@Test
	@Rollback(false)
	public void add() {
		DutySection dutySection = new DutySection();

		this.dutySectionDao.save(dutySection);
	}
}
