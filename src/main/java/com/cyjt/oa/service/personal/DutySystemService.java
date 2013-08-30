package com.cyjt.oa.service.personal;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.personal.DutySystem;

public abstract interface DutySystemService extends BaseService<DutySystem> {
	public abstract DutySystem getDefaultDutySystem();
}
