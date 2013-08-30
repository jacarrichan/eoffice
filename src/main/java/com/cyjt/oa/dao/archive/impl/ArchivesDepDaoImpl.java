package com.cyjt.oa.dao.archive.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.archive.ArchivesDepDao;
import com.cyjt.oa.model.archive.ArchivesDep;

public class ArchivesDepDaoImpl extends BaseDaoImpl<ArchivesDep> implements
		ArchivesDepDao {
	public ArchivesDepDaoImpl() {
		super(ArchivesDep.class);
	}
}
