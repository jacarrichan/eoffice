package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.archive.ArchHastenDao;
import com.cyjt.oa.model.archive.ArchHasten;
import com.cyjt.oa.service.archive.ArchHastenService;
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
