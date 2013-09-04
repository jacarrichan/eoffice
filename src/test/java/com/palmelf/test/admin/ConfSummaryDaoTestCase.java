package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.ConfSummaryDao;
import com.palmelf.eoffice.model.admin.ConfSummary;
import com.palmelf.test.BaseTestCase;

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
