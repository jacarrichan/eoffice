package com.palmelf.eoffice.dao.admin;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.admin.DepreRecord;

import java.util.Date;

public abstract interface DepreRecordDao extends BaseDao<DepreRecord> {
	public abstract Date findMaxDate(Long paramLong);
}
