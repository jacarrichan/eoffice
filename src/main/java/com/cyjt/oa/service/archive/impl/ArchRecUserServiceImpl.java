package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.archive.ArchRecUserDao;
import com.cyjt.oa.model.archive.ArchRecUser;
import com.cyjt.oa.service.archive.ArchRecUserService;
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
