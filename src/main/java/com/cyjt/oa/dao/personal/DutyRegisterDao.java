package com.cyjt.oa.dao.personal;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.personal.DutyRegister;

public abstract interface DutyRegisterDao extends BaseDao<DutyRegister> {
	public abstract DutyRegister getTodayUserRegister(Long paramLong1,
			Short paramShort, Long paramLong2);
}
