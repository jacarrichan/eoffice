package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.CarApplyDao;
import com.palmelf.eoffice.model.admin.CarApply;

public class CarApplyDaoImpl extends BaseDaoImpl<CarApply> implements
		CarApplyDao {
	public CarApplyDaoImpl() {
		super(CarApply.class);
	}
}
