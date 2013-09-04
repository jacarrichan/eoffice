package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.archive.ArchRecTypeDao;
import com.palmelf.eoffice.model.archive.ArchRecType;
import com.palmelf.eoffice.service.archive.ArchRecTypeService;

public class ArchRecTypeServiceImpl extends BaseServiceImpl<ArchRecType>
		implements ArchRecTypeService {
	private ArchRecTypeDao dao;

	public ArchRecTypeServiceImpl(ArchRecTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
