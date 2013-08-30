package com.cyjt.oa.service.customer;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.customer.Project;

public abstract interface ProjectService extends BaseService<Project> {
	public abstract boolean checkProjectNo(String paramString);
}
