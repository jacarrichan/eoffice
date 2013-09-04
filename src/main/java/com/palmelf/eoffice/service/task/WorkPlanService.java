package com.palmelf.eoffice.service.task;

import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.task.WorkPlan;

import java.util.List;

public abstract interface WorkPlanService extends BaseService<WorkPlan> {
	public abstract List<WorkPlan> findByDepartment(WorkPlan paramWorkPlan,
			AppUser paramAppUser, PagingBean paramPagingBean);
}
