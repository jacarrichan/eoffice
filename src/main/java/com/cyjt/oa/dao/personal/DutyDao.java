package com.cyjt.oa.dao.personal;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.personal.Duty;
import java.util.Date;
import java.util.List;

public abstract interface DutyDao extends BaseDao<Duty> {
	public abstract List<Duty> getUserDutyByTime(Long paramLong,
			Date paramDate1, Date paramDate2);

	public abstract List<Duty> getCurUserDuty(Long paramLong);
}
