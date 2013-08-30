package com.cyjt.test.admin;

import com.cyjt.oa.dao.admin.ConfSummaryDao;
import com.cyjt.oa.model.admin.ConfSummary;
import com.cyjt.test.BaseTestCase;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ConfSummaryDaoTestCase extends BaseTestCase {

	@Resource
	private ConfSummaryDao confSummaryDao;

	@Test
	@Rollback(false)
	public void add() {
		ConfSummary confSummary = new ConfSummary();

		this.confSummaryDao.save(confSummary);
	}
}
