package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.archive.ArchRecUserDao;
import com.palmelf.eoffice.model.archive.ArchRecUser;
import com.palmelf.eoffice.service.archive.ArchRecUserService;

import java.util.List;

public class ArchRecUserServiceImpl extends BaseServiceImpl<ArchRecUser>
		implements ArchRecUserService {
	private ArchRecUserDao dao;

	public ArchRecUserServiceImpl(ArchRecUserDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List findDepAll() {
		return this.dao.findDepAll();
	}

	public ArchRecUser getByDepId(Long depId) {
		return this.dao.getByDepId(depId);
	}
}
