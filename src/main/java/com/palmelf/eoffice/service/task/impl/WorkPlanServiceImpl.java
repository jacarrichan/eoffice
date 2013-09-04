package com.palmelf.eoffice.service.task.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.task.WorkPlanDao;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.task.WorkPlan;
import com.palmelf.eoffice.service.task.WorkPlanService;

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
