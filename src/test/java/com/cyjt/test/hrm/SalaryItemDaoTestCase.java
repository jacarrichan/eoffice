package com.cyjt.test.hrm;

import com.cyjt.oa.dao.hrm.SalaryItemDao;
import com.cyjt.oa.model.hrm.SalaryItem;
import com.cyjt.test.BaseTestCase;
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
