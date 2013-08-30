package com.cyjt.oa.dao.hrm;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.hrm.Job;
import java.util.List;

public abstract interface JobDao extends BaseDao<Job> {
	public abstract List<Job> findByDep(Long paramLong);
}
