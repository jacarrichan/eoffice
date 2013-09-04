package com.palmelf.eoffice.dao.archive.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.archive.LeaderReadDao;
import com.palmelf.eoffice.model.archive.LeaderRead;

public class LeaderReadDaoImpl extends BaseDaoImpl<LeaderRead> implements
		LeaderReadDao {
	public LeaderReadDaoImpl() {
		super(LeaderRead.class);
	}
}
