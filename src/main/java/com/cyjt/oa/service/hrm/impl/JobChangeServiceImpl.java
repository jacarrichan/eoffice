package com.cyjt.oa.service.hrm.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.hrm.JobChangeDao;
import com.cyjt.oa.model.hrm.JobChange;
import com.cyjt.oa.service.hrm.JobChangeService;

public class JobChangeServiceImpl extends BaseServiceImpl<JobChange> implements
		JobChangeService {
	private JobChangeDao dao;

	public JobChangeServiceImpl(JobChangeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
