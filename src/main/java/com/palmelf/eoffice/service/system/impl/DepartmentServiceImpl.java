package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.system.DepartmentDao;
import com.palmelf.eoffice.model.system.Department;
import com.palmelf.eoffice.service.system.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl extends BaseServiceImpl<Department>
		implements DepartmentService {
	private DepartmentDao dao;

	public DepartmentServiceImpl(DepartmentDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Department> findByParentId(Long parentId) {
		return this.dao.findByParentId(parentId);
	}

	public List<Department> findByDepName(String depName) {
		return this.dao.findByDepName(depName);
	}

	public List<Department> findByPath(String path) {
		return this.dao.findByPath(path);
	}
}
