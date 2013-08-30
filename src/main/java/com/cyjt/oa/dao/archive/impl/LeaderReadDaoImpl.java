package com.cyjt.oa.dao.archive.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.archive.LeaderReadDao;
import com.cyjt.oa.model.archive.LeaderRead;

public class LeaderReadDaoImpl extends BaseDaoImpl<LeaderRead> implements
		LeaderReadDao {
	public LeaderReadDaoImpl() {
		super(LeaderRead.class);
	}
}
