package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.archive.ArchivesTypeDao;
import com.palmelf.eoffice.model.archive.ArchivesType;
import com.palmelf.eoffice.service.archive.ArchivesTypeService;

public class ArchivesTypeServiceImpl extends BaseServiceImpl<ArchivesType>
		implements ArchivesTypeService {
	private ArchivesTypeDao dao;

	public ArchivesTypeServiceImpl(ArchivesTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
