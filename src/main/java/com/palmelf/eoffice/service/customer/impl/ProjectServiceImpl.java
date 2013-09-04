package com.palmelf.eoffice.service.customer.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.customer.ProjectDao;
import com.palmelf.eoffice.model.customer.Project;
import com.palmelf.eoffice.service.customer.ProjectService;

public class ProjectServiceImpl extends BaseServiceImpl<Project> implements
		ProjectService {
	private ProjectDao dao;

	public ProjectServiceImpl(ProjectDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean checkProjectNo(String projectNo) {
		return this.dao.checkProjectNo(projectNo);
	}
}
