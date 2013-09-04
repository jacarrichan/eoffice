package com.palmelf.eoffice.dao.archive.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.archive.ArchivesDepDao;
import com.palmelf.eoffice.model.archive.ArchivesDep;

public class ArchivesDepDaoImpl extends BaseDaoImpl<ArchivesDep> implements
		ArchivesDepDao {
	public ArchivesDepDaoImpl() {
		super(ArchivesDep.class);
	}
}
