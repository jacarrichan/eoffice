package com.palmelf.eoffice.service.personal;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.personal.DutyRegister;
import com.palmelf.eoffice.model.system.AppUser;

import java.util.Date;

public abstract interface DutyRegisterService extends BaseService<DutyRegister> {
	public abstract void signInOff(Long paramLong, Short paramShort,
			AppUser paramAppUser, Date paramDate);

	public abstract DutyRegister getTodayUserRegister(Long paramLong1,
			Short paramShort, Long paramLong2);
}
