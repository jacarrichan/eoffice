package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.CarApplyDao;
import com.palmelf.eoffice.model.admin.CarApply;
import com.palmelf.eoffice.service.admin.CarApplyService;

public class CarApplyServiceImpl extends BaseServiceImpl<CarApply> implements
		CarApplyService {
	private CarApplyDao dao;

	public CarApplyServiceImpl(CarApplyDao dao) {
		super(dao);
		this.dao = dao;
	}
}
