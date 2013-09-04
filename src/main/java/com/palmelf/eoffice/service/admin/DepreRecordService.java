package com.palmelf.eoffice.service.admin;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.admin.DepreRecord;

import java.util.Date;

public abstract interface DepreRecordService extends BaseService<DepreRecord> {
	public abstract Date findMaxDate(Long paramLong);
}
