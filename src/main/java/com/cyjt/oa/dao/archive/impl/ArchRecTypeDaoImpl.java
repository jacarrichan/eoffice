package com.cyjt.oa.dao.archive.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.archive.ArchRecTypeDao;
import com.cyjt.oa.model.archive.ArchRecType;

public class ArchRecTypeDaoImpl extends BaseDaoImpl<ArchRecType> implements
		ArchRecTypeDao {
	public ArchRecTypeDaoImpl() {
		super(ArchRecType.class);
	}
}
