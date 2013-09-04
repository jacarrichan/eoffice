package com.palmelf.eoffice.dao.hrm.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.hrm.JobDao;
import com.palmelf.eoffice.model.hrm.Job;

import java.util.List;

public class JobDaoImpl extends BaseDaoImpl<Job> implements JobDao {
	public JobDaoImpl() {
		super(Job.class);
	}

	public List<Job> findByDep(Long depId) {
		String hql = "from Job vo where vo.department.depId=?";
		Object[] objs = { depId };
		return findByHql(hql, objs);
	}
}
