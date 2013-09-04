package com.palmelf.test.hrm;

import com.palmelf.eoffice.dao.hrm.StandSalaryItemDao;
import com.palmelf.eoffice.model.hrm.StandSalaryItem;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class StandSalaryItemDaoTestCase extends BaseTestCase {

	@Resource
	private StandSalaryItemDao standSalaryItemDao;

	@Test
	@Rollback(false)
	public void add() {
		StandSalaryItem standSalaryItem = new StandSalaryItem();

		this.standSalaryItemDao.save(standSalaryItem);
	}
}
