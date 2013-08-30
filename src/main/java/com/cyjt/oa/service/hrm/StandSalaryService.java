package com.cyjt.oa.service.hrm;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.hrm.StandSalary;
import java.util.List;

public abstract interface StandSalaryService extends BaseService<StandSalary> {
	public abstract boolean checkStandNo(String paramString);

	public abstract List<StandSalary> findByPassCheck();
}
