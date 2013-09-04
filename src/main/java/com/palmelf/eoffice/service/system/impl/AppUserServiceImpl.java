package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.system.AppUserDao;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.system.AppUserService;

import java.util.List;
import java.util.Set;

public class AppUserServiceImpl extends BaseServiceImpl<AppUser> implements
		AppUserService {
	private AppUserDao dao;

	public AppUserServiceImpl(AppUserDao dao) {
		super(dao);
		this.dao = dao;
	}

	public AppUser findByUserName(String username) {
		return this.dao.findByUserName(username);
	}

	public List findByDepartment(String path, PagingBean pb) {
		return this.dao.findByDepartment(path, pb);
	}

	public List<AppUser> findByRole(Long roleId, PagingBean pb) {
		return this.dao.findByRole(roleId, pb);
	}

	public List findByRoleId(Long roleId) {
		return this.dao.findByRole(roleId);
	}

	public List<AppUser> findSubAppUser(String path, Set<Long> userIds,
			PagingBean pb) {
		return this.dao.findSubAppUser(path, userIds, pb);
	}

	public List<AppUser> findSubAppUserByRole(Long roleId, Set<Long> userIds,
			PagingBean pb) {
		return this.dao.findSubAppUserByRole(roleId, userIds, pb);
	}

	public List<AppUser> findByDepId(Long depId) {
		return this.dao.findByDepId(depId);
	}

	public List<AppUser> getUnAgents(Long userId, String fullname, PagingBean pb) {
		return this.dao.getUnAgents(userId, fullname, pb);
	}

	public List<AppUser> getUnSubUser(Long userId, String fullname,
			PagingBean pb) {
		return null;
	}
}
