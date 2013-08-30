package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.archive.ArchRecTypeDao;
import com.cyjt.oa.model.archive.ArchRecType;
import com.cyjt.oa.service.archive.ArchRecTypeService;

public class ArchRecTypeServiceImpl extends BaseServiceImpl<ArchRecType>
		implements ArchRecTypeService {
	private ArchRecTypeDao dao;

	public ArchRecTypeServiceImpl(ArchRecTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
