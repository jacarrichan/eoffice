package com.cyjt.oa.service.hrm.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.hrm.SalaryPayoffDao;
import com.cyjt.oa.model.hrm.SalaryPayoff;
import com.cyjt.oa.service.hrm.SalaryPayoffService;

public class SalaryPayoffServiceImpl extends BaseServiceImpl<SalaryPayoff>
		implements SalaryPayoffService {
	private SalaryPayoffDao dao;

	public SalaryPayoffServiceImpl(SalaryPayoffDao dao) {
		super(dao);
		this.dao = dao;
	}
}
