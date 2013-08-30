package com.cyjt.oa.service.system;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.system.Department;
import java.util.List;

public abstract interface DepartmentService extends BaseService<Department> {
	public abstract List<Department> findByParentId(Long paramLong);

	public abstract List<Department> findByDepName(String paramString);

	public abstract List<Department> findByPath(String paramString);
}
