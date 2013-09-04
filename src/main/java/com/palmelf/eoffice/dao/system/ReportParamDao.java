package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.system.ReportParam;

import java.util.List;

public abstract interface ReportParamDao extends BaseDao<ReportParam> {
	public abstract List<ReportParam> findByRepTemp(Long paramLong);
}
