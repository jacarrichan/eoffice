package com.palmelf.eoffice.dao.hrm.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.hrm.SalaryPayoffDao;
import com.palmelf.eoffice.model.hrm.SalaryPayoff;

public class SalaryPayoffDaoImpl extends BaseDaoImpl<SalaryPayoff> implements
		SalaryPayoffDao {
	public SalaryPayoffDaoImpl() {
		super(SalaryPayoff.class);
	}
}
