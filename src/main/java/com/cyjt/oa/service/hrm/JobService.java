package com.cyjt.oa.service.hrm;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.hrm.Job;
import java.util.List;

public abstract interface JobService extends BaseService<Job> {
	public abstract List<Job> findByDep(Long paramLong);
}
