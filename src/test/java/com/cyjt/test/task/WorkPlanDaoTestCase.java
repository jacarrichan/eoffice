package com.cyjt.test.task;

import com.cyjt.oa.dao.task.WorkPlanDao;
import com.cyjt.oa.model.task.WorkPlan;
import com.cyjt.test.BaseTestCase;
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
