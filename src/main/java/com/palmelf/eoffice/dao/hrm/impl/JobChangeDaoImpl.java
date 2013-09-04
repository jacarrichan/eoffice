package com.palmelf.eoffice.dao.hrm.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.hrm.JobChangeDao;
import com.palmelf.eoffice.model.hrm.JobChange;

public class JobChangeDaoImpl extends BaseDaoImpl<JobChange> implements
		JobChangeDao {
	public JobChangeDaoImpl() {
		super(JobChange.class);
	}
}
