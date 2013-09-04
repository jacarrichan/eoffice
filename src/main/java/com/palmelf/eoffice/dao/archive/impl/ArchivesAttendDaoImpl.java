package com.palmelf.eoffice.dao.archive.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.archive.ArchivesAttendDao;
import com.palmelf.eoffice.model.archive.ArchivesAttend;

public class ArchivesAttendDaoImpl extends BaseDaoImpl<ArchivesAttend>
		implements ArchivesAttendDao {
	public ArchivesAttendDaoImpl() {
		super(ArchivesAttend.class);
	}
}
