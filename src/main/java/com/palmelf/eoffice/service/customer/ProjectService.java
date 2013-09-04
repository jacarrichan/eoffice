package com.palmelf.eoffice.service.customer;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.customer.Project;

public abstract interface ProjectService extends BaseService<Project> {
	public abstract boolean checkProjectNo(String paramString);
}
