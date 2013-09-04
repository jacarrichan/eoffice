package com.palmelf.eoffice.dao.archive.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.archive.ArchRecTypeDao;
import com.palmelf.eoffice.model.archive.ArchRecType;

public class ArchRecTypeDaoImpl extends BaseDaoImpl<ArchRecType> implements
		ArchRecTypeDao {
	public ArchRecTypeDaoImpl() {
		super(ArchRecType.class);
	}
}
