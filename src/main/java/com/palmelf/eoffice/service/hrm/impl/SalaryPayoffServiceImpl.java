package com.palmelf.eoffice.service.hrm.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.hrm.SalaryPayoffDao;
import com.palmelf.eoffice.model.hrm.SalaryPayoff;
import com.palmelf.eoffice.service.hrm.SalaryPayoffService;

public class SalaryPayoffServiceImpl extends BaseServiceImpl<SalaryPayoff>
		implements SalaryPayoffService {
	private SalaryPayoffDao dao;

	public SalaryPayoffServiceImpl(SalaryPayoffDao dao) {
		super(dao);
		this.dao = dao;
	}
}
