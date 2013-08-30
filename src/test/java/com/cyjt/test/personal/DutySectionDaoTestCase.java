package com.cyjt.test.personal;

import com.cyjt.oa.dao.personal.DutySectionDao;
import com.cyjt.oa.model.personal.DutySection;
import com.cyjt.test.BaseTestCase;
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
