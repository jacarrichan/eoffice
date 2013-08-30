package com.cyjt.oa.service.personal;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.personal.DutyRegister;
import com.cyjt.oa.model.system.AppUser;
import java.util.Date;

public abstract interface DutyRegisterService extends BaseService<DutyRegister> {
	public abstract void signInOff(Long paramLong, Short paramShort,
			AppUser paramAppUser, Date paramDate);

	public abstract DutyRegister getTodayUserRegister(Long paramLong1,
			Short paramShort, Long paramLong2);
}
