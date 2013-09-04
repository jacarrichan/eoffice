package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.archive.ArchivesDocDao;
import com.palmelf.eoffice.model.archive.ArchivesDoc;
import com.palmelf.eoffice.service.archive.ArchivesDocService;

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
