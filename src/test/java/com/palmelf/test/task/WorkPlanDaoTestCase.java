package com.palmelf.test.task;

import com.palmelf.eoffice.dao.task.WorkPlanDao;
import com.palmelf.eoffice.model.task.WorkPlan;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class WorkPlanDaoTestCase extends BaseTestCase {

	@Resource
	private WorkPlanDao workPlanDao;

	@Test
	@Rollback(false)
	public void add() {
		WorkPlan workPlan = new WorkPlan();

		this.workPlanDao.save(workPlan);
	}
}
