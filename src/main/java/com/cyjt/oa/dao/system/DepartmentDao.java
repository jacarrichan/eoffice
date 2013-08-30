package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.system.Department;
import java.util.List;

public abstract interface DepartmentDao extends BaseDao<Department> {
	public abstract List<Department> findByParentId(Long paramLong);

	public abstract List<Department> findByVo(Department paramDepartment,
			PagingBean paramPagingBean);

	public abstract List<Department> findByDepName(String paramString);

	public abstract List<Department> findByPath(String paramString);
}
