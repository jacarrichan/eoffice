package com.cyjt.oa.dao.admin;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.admin.DepreRecord;
import java.util.Date;

public abstract interface DepreRecordDao extends BaseDao<DepreRecord> {
	public abstract Date findMaxDate(Long paramLong);
}
