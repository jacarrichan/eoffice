package com.palmelf.eoffice.dao.personal;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.personal.Duty;

import java.util.Date;
import java.util.List;

public abstract interface DutyDao extends BaseDao<Duty> {
	public abstract List<Duty> getUserDutyByTime(Long paramLong,
			Date paramDate1, Date paramDate2);

	public abstract List<Duty> getCurUserDuty(Long paramLong);
}
