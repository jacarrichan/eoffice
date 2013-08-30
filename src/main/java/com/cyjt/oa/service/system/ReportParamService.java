package com.cyjt.oa.service.system;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.system.ReportParam;
import java.util.List;

public abstract interface ReportParamService extends BaseService<ReportParam> {
	public abstract List<ReportParam> findByRepTemp(Long paramLong);
}
