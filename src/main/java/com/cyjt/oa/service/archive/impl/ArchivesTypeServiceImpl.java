package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.archive.ArchivesTypeDao;
import com.cyjt.oa.model.archive.ArchivesType;
import com.cyjt.oa.service.archive.ArchivesTypeService;

public class ArchivesTypeServiceImpl extends BaseServiceImpl<ArchivesType>
		implements ArchivesTypeService {
	private ArchivesTypeDao dao;

	public ArchivesTypeServiceImpl(ArchivesTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
