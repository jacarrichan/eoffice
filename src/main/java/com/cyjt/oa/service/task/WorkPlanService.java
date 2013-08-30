package com.cyjt.oa.service.task;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.task.WorkPlan;
import java.util.List;

public abstract interface WorkPlanService extends BaseService<WorkPlan> {
	public abstract List<WorkPlan> findByDepartment(WorkPlan paramWorkPlan,
			AppUser paramAppUser, PagingBean paramPagingBean);
}
