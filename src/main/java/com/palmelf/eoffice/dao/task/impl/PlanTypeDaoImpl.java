package com.palmelf.eoffice.dao.task.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.task.PlanTypeDao;
import com.palmelf.eoffice.model.task.PlanType;

public class PlanTypeDaoImpl extends BaseDaoImpl<PlanType> implements
		PlanTypeDao {
	public PlanTypeDaoImpl() {
		super(PlanType.class);
	}
}
