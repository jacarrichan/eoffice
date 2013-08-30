package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.archive.ArchivesDepDao;
import com.cyjt.oa.model.archive.ArchivesDep;
import com.cyjt.oa.service.archive.ArchivesDepService;

public class ArchivesDepServiceImpl extends BaseServiceImpl<ArchivesDep>
		implements ArchivesDepService {
	private ArchivesDepDao dao;

	public ArchivesDepServiceImpl(ArchivesDepDao dao) {
		super(dao);
		this.dao = dao;
	}
}
