package com.cyjt.oa.service.arch.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.arch.ArchRollDao;
import com.cyjt.oa.model.arch.ArchRoll;
import com.cyjt.oa.service.arch.ArchRollService;

public class ArchRollServiceImpl extends BaseServiceImpl<ArchRoll> implements
		ArchRollService {
	private ArchRollDao dao;

	public ArchRollServiceImpl(ArchRollDao dao) {
		super(dao);
		this.dao = dao;
	}
}
