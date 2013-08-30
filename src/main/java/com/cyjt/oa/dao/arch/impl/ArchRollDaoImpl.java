package com.cyjt.oa.dao.arch.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.arch.ArchRollDao;
import com.cyjt.oa.model.arch.ArchRoll;

public class ArchRollDaoImpl extends BaseDaoImpl<ArchRoll> implements
		ArchRollDao {
	public ArchRollDaoImpl() {
		super(ArchRoll.class);
	}
}
