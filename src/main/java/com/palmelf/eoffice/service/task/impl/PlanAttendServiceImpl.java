package com.palmelf.eoffice.service.task.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.task.PlanAttendDao;
import com.palmelf.eoffice.model.task.PlanAttend;
import com.palmelf.eoffice.service.task.PlanAttendService;

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
