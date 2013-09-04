package com.palmelf.eoffice.dao.arch.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.arch.ArchFondDao;
import com.palmelf.eoffice.model.arch.ArchFond;

public class ArchFondDaoImpl extends BaseDaoImpl<ArchFond> implements
		ArchFondDao {
	public ArchFondDaoImpl() {
		super(ArchFond.class);
	}
}
