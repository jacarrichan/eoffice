package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.archive.ArchivesAttendDao;
import com.palmelf.eoffice.model.archive.ArchivesAttend;
import com.palmelf.eoffice.service.archive.ArchivesAttendService;

public class ArchivesAttendServiceImpl extends BaseServiceImpl<ArchivesAttend>
		implements ArchivesAttendService {
	private ArchivesAttendDao dao;

	public ArchivesAttendServiceImpl(ArchivesAttendDao dao) {
		super(dao);
		this.dao = dao;
	}
}
