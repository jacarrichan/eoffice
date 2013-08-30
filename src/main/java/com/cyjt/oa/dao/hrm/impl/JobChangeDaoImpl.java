package com.cyjt.oa.dao.hrm.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.hrm.JobChangeDao;
import com.cyjt.oa.model.hrm.JobChange;

public class JobChangeDaoImpl extends BaseDaoImpl<JobChange> implements
		JobChangeDao {
	public JobChangeDaoImpl() {
		super(JobChange.class);
	}
}
