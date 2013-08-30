package com.cyjt.oa.service.task.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.task.PlanAttendDao;
import com.cyjt.oa.model.task.PlanAttend;
import com.cyjt.oa.service.task.PlanAttendService;
import java.util.List;

public class PlanAttendServiceImpl extends BaseServiceImpl<PlanAttend>
		implements PlanAttendService {
	private PlanAttendDao dao;

	public PlanAttendServiceImpl(PlanAttendDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean deletePlanAttend(Long planId, Short isDep, Short isPrimary) {
		List<PlanAttend> list = this.dao.FindPlanAttend(planId, isDep,
				isPrimary);
		for (PlanAttend pa : list) {
			this.dao.remove(pa.getAttendId());
		}
		return true;
	}
}
