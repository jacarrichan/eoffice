package com.cyjt.oa.service.task.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.task.WorkPlanDao;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.task.WorkPlan;
import com.cyjt.oa.service.task.WorkPlanService;
import java.util.List;

public class WorkPlanServiceImpl extends BaseServiceImpl<WorkPlan> implements
		WorkPlanService {
	private WorkPlanDao dao;

	public WorkPlanServiceImpl(WorkPlanDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<WorkPlan> findByDepartment(WorkPlan workPlan, AppUser user,
			PagingBean pb) {
		return this.dao.findByDepartment(workPlan, user, pb);
	}
}
