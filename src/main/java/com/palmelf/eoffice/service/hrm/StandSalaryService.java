package com.palmelf.eoffice.service.hrm;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.hrm.StandSalary;

import java.util.List;

public abstract interface StandSalaryService extends BaseService<StandSalary> {
	public abstract boolean checkStandNo(String paramString);

	public abstract List<StandSalary> findByPassCheck();
}
