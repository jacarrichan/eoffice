package com.palmelf.eoffice.service.hrm.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.hrm.JobChangeDao;
import com.palmelf.eoffice.model.hrm.JobChange;
import com.palmelf.eoffice.service.hrm.JobChangeService;

public class JobChangeServiceImpl extends BaseServiceImpl<JobChange> implements
		JobChangeService {
	private JobChangeDao dao;

	public JobChangeServiceImpl(JobChangeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
