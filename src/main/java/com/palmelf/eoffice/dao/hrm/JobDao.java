package com.palmelf.eoffice.dao.hrm;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.hrm.Job;

import java.util.List;

public abstract interface JobDao extends BaseDao<Job> {
	public abstract List<Job> findByDep(Long paramLong);
}
