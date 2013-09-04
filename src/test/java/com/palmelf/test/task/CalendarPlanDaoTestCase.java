package com.palmelf.test.task;

import com.palmelf.eoffice.dao.task.CalendarPlanDao;
import com.palmelf.eoffice.model.task.CalendarPlan;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class CalendarPlanDaoTestCase extends BaseTestCase {

	@Resource
	private CalendarPlanDao calendarPlanDao;

	@Test
	@Rollback(false)
	public void add() {
		CalendarPlan calendarPlan = new CalendarPlan();

		this.calendarPlanDao.save(calendarPlan);
	}
}
