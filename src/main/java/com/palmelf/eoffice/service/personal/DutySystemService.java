package com.palmelf.eoffice.service.personal;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.personal.DutySystem;

public abstract interface DutySystemService extends BaseService<DutySystem> {
	public abstract DutySystem getDefaultDutySystem();
}
