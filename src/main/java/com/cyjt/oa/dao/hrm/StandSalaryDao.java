package com.cyjt.oa.dao.hrm;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.hrm.StandSalary;
import java.util.List;

public abstract interface StandSalaryDao extends BaseDao<StandSalary> {
	public abstract boolean checkStandNo(String paramString);

	public abstract List<StandSalary> findByPassCheck();
}
