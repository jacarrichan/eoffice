package com.palmelf.test.hrm;

import com.palmelf.eoffice.dao.hrm.SalaryItemDao;
import com.palmelf.eoffice.model.hrm.SalaryItem;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class SalaryItemDaoTestCase extends BaseTestCase {

	@Resource
	private SalaryItemDao salaryItemDao;

	@Test
	@Rollback(false)
	public void add() {
		SalaryItem salaryItem = new SalaryItem();

		this.salaryItemDao.save(salaryItem);
	}
}
