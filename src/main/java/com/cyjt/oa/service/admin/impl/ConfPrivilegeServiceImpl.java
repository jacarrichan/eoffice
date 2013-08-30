package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.oa.dao.admin.ConfPrivilegeDao;
import com.cyjt.oa.model.admin.ConfPrivilege;
import com.cyjt.oa.service.admin.ConfPrivilegeService;

public class ConfPrivilegeServiceImpl extends BaseServiceImpl<ConfPrivilege>
		implements ConfPrivilegeService {
	private ConfPrivilegeDao dao;

	public ConfPrivilegeServiceImpl(ConfPrivilegeDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Short getPrivilege(Long confId, Short s) {
		return this.dao.getPrivilege(ContextUtil.getCurrentUserId(), confId, s);
	}
}
