package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.system.ReportParam;

import java.util.List;

public abstract interface ReportParamService extends BaseService<ReportParam> {
	public abstract List<ReportParam> findByRepTemp(Long paramLong);
}
