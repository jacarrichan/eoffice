package com.cyjt.oa.dao.task;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.task.PlanAttend;
import java.util.List;

public abstract interface PlanAttendDao extends BaseDao<PlanAttend> {
	public abstract List<PlanAttend> FindPlanAttend(Long paramLong,
			Short paramShort1, Short paramShort2);
}
