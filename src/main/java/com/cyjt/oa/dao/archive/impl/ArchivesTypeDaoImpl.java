package com.cyjt.oa.dao.archive.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.archive.ArchivesTypeDao;
import com.cyjt.oa.model.archive.ArchivesType;

public class ArchivesTypeDaoImpl extends BaseDaoImpl<ArchivesType> implements
		ArchivesTypeDao {
	public ArchivesTypeDaoImpl() {
		super(ArchivesType.class);
	}
}
