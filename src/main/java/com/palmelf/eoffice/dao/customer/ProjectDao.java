package com.palmelf.eoffice.dao.customer;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.customer.Project;

public abstract interface ProjectDao extends BaseDao<Project> {
	public abstract boolean checkProjectNo(String paramString);
}
