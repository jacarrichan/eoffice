package com.cyjt.oa.dao.task.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.task.PlanTypeDao;
import com.cyjt.oa.model.task.PlanType;

public class PlanTypeDaoImpl extends BaseDaoImpl<PlanType> implements
		PlanTypeDao {
	public PlanTypeDaoImpl() {
		super(PlanType.class);
	}
}
