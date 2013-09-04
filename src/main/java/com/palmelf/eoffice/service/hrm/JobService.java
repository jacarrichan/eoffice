package com.palmelf.eoffice.service.hrm;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.hrm.Job;

import java.util.List;

public abstract interface JobService extends BaseService<Job> {
	public abstract List<Job> findByDep(Long paramLong);
}
