package com.palmelf.eoffice.service.task;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.task.PlanAttend;

public abstract interface PlanAttendService extends BaseService<PlanAttend> {
	public abstract boolean deletePlanAttend(Long paramLong, Short paramShort1,
			Short paramShort2);
}
