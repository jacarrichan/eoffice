package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.archive.ArchivesDocDao;
import com.cyjt.oa.model.archive.ArchivesDoc;
import com.cyjt.oa.service.archive.ArchivesDocService;
import java.util.List;

public class ArchivesDocServiceImpl extends BaseServiceImpl<ArchivesDoc>
		implements ArchivesDocService {
	private ArchivesDocDao dao;

	public ArchivesDocServiceImpl(ArchivesDocDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<ArchivesDoc> findByAid(Long archivesId) {
		return this.dao.findByAid(archivesId);
	}
}
