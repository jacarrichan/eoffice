package com.cyjt.oa.service.arch.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.arch.ArchFondDao;
import com.cyjt.oa.model.arch.ArchFond;
import com.cyjt.oa.service.arch.ArchFondService;

public class ArchFondServiceImpl extends BaseServiceImpl<ArchFond> implements
		ArchFondService {
	private ArchFondDao dao;

	public ArchFondServiceImpl(ArchFondDao dao) {
		super(dao);
		this.dao = dao;
	}
}
