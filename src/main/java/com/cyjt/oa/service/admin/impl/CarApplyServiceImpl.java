package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.CarApplyDao;
import com.cyjt.oa.model.admin.CarApply;
import com.cyjt.oa.service.admin.CarApplyService;

public class CarApplyServiceImpl extends BaseServiceImpl<CarApply> implements
		CarApplyService {
	private CarApplyDao dao;

	public CarApplyServiceImpl(CarApplyDao dao) {
		super(dao);
		this.dao = dao;
	}
}
