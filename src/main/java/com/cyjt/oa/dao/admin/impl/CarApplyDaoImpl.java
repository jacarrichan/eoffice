package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.CarApplyDao;
import com.cyjt.oa.model.admin.CarApply;

public class CarApplyDaoImpl extends BaseDaoImpl<CarApply> implements
		CarApplyDao {
	public CarApplyDaoImpl() {
		super(CarApply.class);
	}
}
