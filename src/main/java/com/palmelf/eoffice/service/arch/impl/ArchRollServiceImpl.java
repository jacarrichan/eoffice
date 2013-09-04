package com.palmelf.eoffice.service.arch.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.arch.ArchRollDao;
import com.palmelf.eoffice.model.arch.ArchRoll;
import com.palmelf.eoffice.service.arch.ArchRollService;

public class ArchRollServiceImpl extends BaseServiceImpl<ArchRoll> implements
		ArchRollService {
	private ArchRollDao dao;

	public ArchRollServiceImpl(ArchRollDao dao) {
		super(dao);
		this.dao = dao;
	}
}
