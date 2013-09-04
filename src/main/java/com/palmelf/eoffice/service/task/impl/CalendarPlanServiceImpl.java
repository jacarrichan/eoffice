package com.palmelf.eoffice.service.task.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.task.CalendarPlanDao;
import com.palmelf.eoffice.model.task.CalendarPlan;
import com.palmelf.eoffice.service.task.CalendarPlanService;

import java.util.Date;
import java.util.List;

public class CalendarPlanServiceImpl extends BaseServiceImpl<CalendarPlan>
		implements CalendarPlanService {
	private CalendarPlanDao dao;

	public CalendarPlanServiceImpl(CalendarPlanDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<CalendarPlan> getTodayPlans(Long userId, PagingBean pb) {
		return this.dao.getTodayPlans(userId, pb);
	}

	public List<CalendarPlan> getByPeriod(Long userId, Date startTime,
			Date endTime) {
		return this.dao.getByPeriod(userId, startTime, endTime);
	}

	public List showCalendarPlanByUserId(Long userId, PagingBean pb) {
		return this.dao.showCalendarPlanByUserId(userId, pb);
	}
}
