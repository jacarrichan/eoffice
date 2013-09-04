package com.palmelf.eoffice.service.personal;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.personal.Duty;

import java.util.Date;

public abstract interface DutyService extends BaseService<Duty> {
	public abstract boolean isExistDutyForUser(Long paramLong, Date paramDate1,
			Date paramDate2);

	public abstract Duty getCurUserDuty(Long paramLong);
}
