package com.cyjt.oa.service.hrm.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.hrm.JobDao;
import com.cyjt.oa.model.hrm.Job;
import com.cyjt.oa.service.hrm.JobService;
import java.util.List;

public class JobServiceImpl extends BaseServiceImpl<Job> implements JobService {
	private JobDao dao;

	public JobServiceImpl(JobDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Job> findByDep(Long depId) {
		return this.dao.findByDep(depId);
	}
}
