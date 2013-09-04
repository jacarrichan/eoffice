package com.palmelf.eoffice.dao.task;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.task.WorkPlan;

import java.util.List;

public abstract interface WorkPlanDao extends BaseDao<WorkPlan> {
	public abstract List<WorkPlan> findByDepartment(WorkPlan paramWorkPlan,
			AppUser paramAppUser, PagingBean paramPagingBean);
}
