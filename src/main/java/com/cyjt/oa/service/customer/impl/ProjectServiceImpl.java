package com.cyjt.oa.service.customer.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.customer.ProjectDao;
import com.cyjt.oa.model.customer.Project;
import com.cyjt.oa.service.customer.ProjectService;

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
