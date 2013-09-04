package com.palmelf.eoffice.dao.task;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.task.CalendarPlan;

import java.util.Date;
import java.util.List;

public abstract interface CalendarPlanDao extends BaseDao<CalendarPlan> {
	public abstract List<CalendarPlan> getTodayPlans(Long paramLong,
			PagingBean paramPagingBean);

	public abstract List<CalendarPlan> getByPeriod(Long paramLong,
			Date paramDate1, Date paramDate2);

	public abstract List showCalendarPlanByUserId(Long paramLong,
			PagingBean paramPagingBean);
}
