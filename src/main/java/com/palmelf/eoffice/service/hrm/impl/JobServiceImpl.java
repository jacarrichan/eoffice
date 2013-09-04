package com.palmelf.eoffice.service.hrm.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.hrm.JobDao;
import com.palmelf.eoffice.model.hrm.Job;
import com.palmelf.eoffice.service.hrm.JobService;

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
