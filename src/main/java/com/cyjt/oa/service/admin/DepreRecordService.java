package com.cyjt.oa.service.admin;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.admin.DepreRecord;
import java.util.Date;

public abstract interface DepreRecordService extends BaseService<DepreRecord> {
	public abstract Date findMaxDate(Long paramLong);
}
