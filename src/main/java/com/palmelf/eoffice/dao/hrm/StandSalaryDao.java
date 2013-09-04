package com.palmelf.eoffice.dao.hrm;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.hrm.StandSalary;

import java.util.List;

public abstract interface StandSalaryDao extends BaseDao<StandSalary> {
	public abstract boolean checkStandNo(String paramString);

	public abstract List<StandSalary> findByPassCheck();
}
