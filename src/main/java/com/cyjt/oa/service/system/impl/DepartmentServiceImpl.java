package com.cyjt.oa.service.system.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.system.DepartmentDao;
import com.cyjt.oa.model.system.Department;
import com.cyjt.oa.service.system.DepartmentService;
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
