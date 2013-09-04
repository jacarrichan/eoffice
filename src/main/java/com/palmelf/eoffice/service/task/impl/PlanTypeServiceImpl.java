package com.palmelf.eoffice.service.task.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.task.PlanTypeDao;
import com.palmelf.eoffice.model.task.PlanType;
import com.palmelf.eoffice.service.task.PlanTypeService;

public class PlanTypeServiceImpl extends BaseServiceImpl<PlanType> implements
		PlanTypeService {
	private PlanTypeDao dao;

	public PlanTypeServiceImpl(PlanTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
