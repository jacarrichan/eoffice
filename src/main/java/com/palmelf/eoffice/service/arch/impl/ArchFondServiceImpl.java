package com.palmelf.eoffice.service.arch.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.arch.ArchFondDao;
import com.palmelf.eoffice.model.arch.ArchFond;
import com.palmelf.eoffice.service.arch.ArchFondService;

public class ArchFondServiceImpl extends BaseServiceImpl<ArchFond> implements
		ArchFondService {
	private ArchFondDao dao;

	public ArchFondServiceImpl(ArchFondDao dao) {
		super(dao);
		this.dao = dao;
	}
}
