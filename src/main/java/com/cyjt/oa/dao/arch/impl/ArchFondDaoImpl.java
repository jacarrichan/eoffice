package com.cyjt.oa.dao.arch.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.arch.ArchFondDao;
import com.cyjt.oa.model.arch.ArchFond;

public class ArchFondDaoImpl extends BaseDaoImpl<ArchFond> implements
		ArchFondDao {
	public ArchFondDaoImpl() {
		super(ArchFond.class);
	}
}
