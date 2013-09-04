package com.palmelf.eoffice.dao.task;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.task.PlanAttend;

import java.util.List;

public abstract interface PlanAttendDao extends BaseDao<PlanAttend> {
	public abstract List<PlanAttend> FindPlanAttend(Long paramLong,
			Short paramShort1, Short paramShort2);
}
