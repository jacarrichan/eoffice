package com.cyjt.oa.service.task;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.task.PlanAttend;

public abstract interface PlanAttendService extends BaseService<PlanAttend> {
	public abstract boolean deletePlanAttend(Long paramLong, Short paramShort1,
			Short paramShort2);
}
