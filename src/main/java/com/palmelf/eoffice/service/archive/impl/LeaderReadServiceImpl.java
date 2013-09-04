package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.archive.LeaderReadDao;
import com.palmelf.eoffice.model.archive.LeaderRead;
import com.palmelf.eoffice.service.archive.LeaderReadService;

public class LeaderReadServiceImpl extends BaseServiceImpl<LeaderRead>
		implements LeaderReadService {
	private LeaderReadDao dao;

	public LeaderReadServiceImpl(LeaderReadDao dao) {
		super(dao);
		this.dao = dao;
	}
}
