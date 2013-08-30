package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.system.ReportParam;
import java.util.List;

public abstract interface ReportParamDao extends BaseDao<ReportParam> {
	public abstract List<ReportParam> findByRepTemp(Long paramLong);
}
