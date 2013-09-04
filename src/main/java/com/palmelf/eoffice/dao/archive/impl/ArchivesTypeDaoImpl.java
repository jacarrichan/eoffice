package com.palmelf.eoffice.dao.archive.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.archive.ArchivesTypeDao;
import com.palmelf.eoffice.model.archive.ArchivesType;

public class ArchivesTypeDaoImpl extends BaseDaoImpl<ArchivesType> implements
		ArchivesTypeDao {
	public ArchivesTypeDaoImpl() {
		super(ArchivesType.class);
	}
}
