package com.cyjt.test.hrm;

import com.cyjt.oa.dao.hrm.StandSalaryItemDao;
import com.cyjt.oa.model.hrm.StandSalaryItem;
import com.cyjt.test.BaseTestCase;
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
