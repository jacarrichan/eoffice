package com.palmelf.eoffice.service.hrm.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.hrm.StandSalaryDao;
import com.palmelf.eoffice.model.hrm.StandSalary;
import com.palmelf.eoffice.service.hrm.StandSalaryService;

import java.util.List;

public class StandSalaryServiceImpl extends BaseServiceImpl<StandSalary>
		implements StandSalaryService {
	private StandSalaryDao dao;

	public StandSalaryServiceImpl(StandSalaryDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean checkStandNo(String standardNo) {
		return this.dao.checkStandNo(standardNo);
	}

	public List<StandSalary> findByPassCheck() {
		return this.dao.findByPassCheck();
	}
}
