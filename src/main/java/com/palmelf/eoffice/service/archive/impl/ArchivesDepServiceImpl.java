package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.archive.ArchivesDepDao;
import com.palmelf.eoffice.model.archive.ArchivesDep;
import com.palmelf.eoffice.service.archive.ArchivesDepService;

public class ArchivesDepServiceImpl extends BaseServiceImpl<ArchivesDep>
		implements ArchivesDepService {
	private ArchivesDepDao dao;

	public ArchivesDepServiceImpl(ArchivesDepDao dao) {
		super(dao);
		this.dao = dao;
	}
}
