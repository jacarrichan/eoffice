package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.archive.ArchHastenDao;
import com.palmelf.eoffice.model.archive.ArchHasten;
import com.palmelf.eoffice.service.archive.ArchHastenService;

import java.util.Date;

public class ArchHastenServiceImpl extends BaseServiceImpl<ArchHasten>
		implements ArchHastenService {
	private ArchHastenDao dao;

	public ArchHastenServiceImpl(ArchHastenDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Date getLeastRecordByUser(Long archivesId) {
		return this.dao.getLeastRecordByUser(archivesId);
	}
}
