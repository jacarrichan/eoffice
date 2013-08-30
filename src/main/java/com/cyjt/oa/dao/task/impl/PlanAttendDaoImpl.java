package com.cyjt.oa.dao.task.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.task.PlanAttendDao;
import com.cyjt.oa.model.task.PlanAttend;
import java.util.ArrayList;
import java.util.List;

public class PlanAttendDaoImpl extends BaseDaoImpl<PlanAttend> implements
		PlanAttendDao {
	public PlanAttendDaoImpl() {
		super(PlanAttend.class);
	}

	public List<PlanAttend> FindPlanAttend(Long planId, Short isDep,
			Short isPrimary) {
		StringBuffer hql = new StringBuffer(
				"from PlanAttend vo where vo.workPlan.planId=?");
		ArrayList list = new ArrayList();
		list.add(planId);
		if (isDep != null) {
			hql.append(" and vo.isDep=?");
			list.add(isDep);
		}
		if (isPrimary != null) {
			hql.append(" and vo.isPrimary=?");
			list.add(isPrimary);
		}
		return findByHql(hql.toString(), list.toArray());
	}
}
