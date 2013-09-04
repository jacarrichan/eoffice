package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.eoffice.dao.admin.ConfPrivilegeDao;
import com.palmelf.eoffice.model.admin.ConfPrivilege;
import com.palmelf.eoffice.service.admin.ConfPrivilegeService;

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
