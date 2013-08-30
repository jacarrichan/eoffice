package com.cyjt.oa.service.task.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.task.PlanTypeDao;
import com.cyjt.oa.model.task.PlanType;
import com.cyjt.oa.service.task.PlanTypeService;

public class PlanTypeServiceImpl extends BaseServiceImpl<PlanType> implements
		PlanTypeService {
	private PlanTypeDao dao;

	public PlanTypeServiceImpl(PlanTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
