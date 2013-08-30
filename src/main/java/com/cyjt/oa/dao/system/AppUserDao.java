package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.Department;
import java.util.List;
import java.util.Set;

public abstract interface AppUserDao extends BaseDao<AppUser> {
	public abstract AppUser findByUserName(String paramString);

	public abstract List<AppUser> findByDepartment(String paramString,
			PagingBean paramPagingBean);

	public abstract List<AppUser> findByDepartment(String paramString);

	public abstract List<AppUser> findByDepartment(Department paramDepartment);

	public abstract List<AppUser> findByRole(Long paramLong);

	public abstract List<AppUser> findByRole(Long paramLong,
			PagingBean paramPagingBean);

	public abstract List<AppUser> findByRoleId(Long paramLong);

	public abstract List<AppUser> findSubAppUser(String paramString,
			Set<Long> paramSet, PagingBean paramPagingBean);

	public abstract List<AppUser> findSubAppUserByRole(Long paramLong,
			Set<Long> paramSet, PagingBean paramPagingBean);

	public abstract List<AppUser> findByDepId(Long paramLong);

	public abstract List<AppUser> getUnAgents(Long paramLong,
			String paramString, PagingBean paramPagingBean);

	public abstract List<AppUser> getUnSubUser(Long paramLong,
			String paramString, PagingBean paramPagingBean);
}
