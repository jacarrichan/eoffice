package com.cyjt.oa.dao.task;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.task.WorkPlan;
import java.util.List;

public abstract interface WorkPlanDao extends BaseDao<WorkPlan> {
	public abstract List<WorkPlan> findByDepartment(WorkPlan paramWorkPlan,
			AppUser paramAppUser, PagingBean paramPagingBean);
}
