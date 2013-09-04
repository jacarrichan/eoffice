package com.palmelf.eoffice.dao.task.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.task.CalendarPlanDao;
import com.palmelf.eoffice.model.task.CalendarPlan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarPlanDaoImpl extends BaseDaoImpl<CalendarPlan> implements
		CalendarPlanDao {
	public CalendarPlanDaoImpl() {
		super(CalendarPlan.class);
	}

	public List<CalendarPlan> getTodayPlans(Long userId, PagingBean pb) {
		String hql = "from CalendarPlan cp where cp.userId=? and cp.startTime<=? and cp.endTime>=?";

		Date curDate = new Date();

		return findByHql(hql, new Object[] { userId, curDate, curDate }, pb);
	}

	public List<CalendarPlan> getByPeriod(Long userId, Date startTime,
			Date endTime) {
		String hql = "from CalendarPlan cp where cp.userId=? and ((cp.startTime>=? and cp.startTime<=?) or (cp.endTime>=? and cp.endTime<=?)) order by cp.planId desc";
		return findByHql(hql, new Object[] { userId, startTime, endTime,
				startTime, endTime });
	}

	public List showCalendarPlanByUserId(Long userId, PagingBean pb) {
		ArrayList paramList = new ArrayList();
		StringBuffer hql = new StringBuffer(
				"select vo from CalendarPlan vo where vo.userId=?");
		paramList.add(userId);
		hql.append(" order by planId desc");
		return findByHql(hql.toString(), paramList.toArray(), pb);
	}
}
