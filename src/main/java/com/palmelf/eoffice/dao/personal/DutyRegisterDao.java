package com.palmelf.eoffice.dao.personal;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.personal.DutyRegister;

public abstract interface DutyRegisterDao extends BaseDao<DutyRegister> {
	public abstract DutyRegister getTodayUserRegister(Long paramLong1,
			Short paramShort, Long paramLong2);
}
