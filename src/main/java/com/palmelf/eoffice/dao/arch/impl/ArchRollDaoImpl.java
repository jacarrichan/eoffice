package com.palmelf.eoffice.dao.arch.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.arch.ArchRollDao;
import com.palmelf.eoffice.model.arch.ArchRoll;

public class ArchRollDaoImpl extends BaseDaoImpl<ArchRoll> implements
		ArchRollDao {
	public ArchRollDaoImpl() {
		super(ArchRoll.class);
	}
}
