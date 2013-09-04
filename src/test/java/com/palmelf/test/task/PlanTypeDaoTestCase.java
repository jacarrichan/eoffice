package com.palmelf.test.task;

import com.palmelf.eoffice.dao.task.PlanTypeDao;
import com.palmelf.eoffice.model.task.PlanType;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class PlanTypeDaoTestCase extends BaseTestCase {

	@Resource
	private PlanTypeDao planTypeDao;

	@Test
	@Rollback(false)
	public void add() {
		PlanType planType = new PlanType();

		this.planTypeDao.save(planType);
	}
}
