package com.cyjt.oa.dao.hrm.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.hrm.JobDao;
import com.cyjt.oa.model.hrm.Job;
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
