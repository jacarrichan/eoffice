package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.archive.ArchivesAttendDao;
import com.cyjt.oa.model.archive.ArchivesAttend;
import com.cyjt.oa.service.archive.ArchivesAttendService;

public class ArchivesAttendServiceImpl extends BaseServiceImpl<ArchivesAttend>
		implements ArchivesAttendService {
	private ArchivesAttendDao dao;

	public ArchivesAttendServiceImpl(ArchivesAttendDao dao) {
		super(dao);
		this.dao = dao;
	}
}
