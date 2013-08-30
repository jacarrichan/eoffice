package com.cyjt.oa.dao.hrm.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.hrm.SalaryPayoffDao;
import com.cyjt.oa.model.hrm.SalaryPayoff;

public class SalaryPayoffDaoImpl extends BaseDaoImpl<SalaryPayoff> implements
		SalaryPayoffDao {
	public SalaryPayoffDaoImpl() {
		super(SalaryPayoff.class);
	}
}
