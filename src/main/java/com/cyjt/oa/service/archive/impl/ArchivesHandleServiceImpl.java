package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.archive.ArchivesHandleDao;
import com.cyjt.oa.model.archive.ArchivesHandle;
import com.cyjt.oa.service.archive.ArchivesHandleService;
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
