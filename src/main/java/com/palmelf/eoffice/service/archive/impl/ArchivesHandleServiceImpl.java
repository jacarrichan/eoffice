package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.archive.ArchivesHandleDao;
import com.palmelf.eoffice.model.archive.ArchivesHandle;
import com.palmelf.eoffice.service.archive.ArchivesHandleService;

import java.util.List;

public class ArchivesHandleServiceImpl extends BaseServiceImpl<ArchivesHandle>
		implements ArchivesHandleService {
	private ArchivesHandleDao dao;

	public ArchivesHandleServiceImpl(ArchivesHandleDao dao) {
		super(dao);
		this.dao = dao;
	}

	public ArchivesHandle findByUAIds(Long userId, Long archiveId) {
		return this.dao.findByUAIds(userId, archiveId);
	}

	public List<ArchivesHandle> findByAid(Long archiveId) {
		return this.dao.findByAid(archiveId);
	}

	public int countHandler(Long archiveId) {
		return this.dao.findByAid(archiveId).size();
	}
}
