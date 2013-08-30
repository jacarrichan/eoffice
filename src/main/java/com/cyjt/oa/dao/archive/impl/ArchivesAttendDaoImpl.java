package com.cyjt.oa.dao.archive.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.archive.ArchivesAttendDao;
import com.cyjt.oa.model.archive.ArchivesAttend;

public class ArchivesAttendDaoImpl extends BaseDaoImpl<ArchivesAttend>
		implements ArchivesAttendDao {
	public ArchivesAttendDaoImpl() {
		super(ArchivesAttend.class);
	}
}
