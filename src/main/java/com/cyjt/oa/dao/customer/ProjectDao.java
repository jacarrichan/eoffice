package com.cyjt.oa.dao.customer;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.customer.Project;

public abstract interface ProjectDao extends BaseDao<Project> {
	public abstract boolean checkProjectNo(String paramString);
}
