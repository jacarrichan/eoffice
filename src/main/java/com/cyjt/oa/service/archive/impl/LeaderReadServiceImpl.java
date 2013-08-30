package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.archive.LeaderReadDao;
import com.cyjt.oa.model.archive.LeaderRead;
import com.cyjt.oa.service.archive.LeaderReadService;

public class LeaderReadServiceImpl extends BaseServiceImpl<LeaderRead>
		implements LeaderReadService {
	private LeaderReadDao dao;

	public LeaderReadServiceImpl(LeaderReadDao dao) {
		super(dao);
		this.dao = dao;
	}
}
