package com.cyjt.oa.service.arch.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.arch.RollFileDao;
import com.cyjt.oa.model.arch.RollFile;
import com.cyjt.oa.service.arch.RollFileService;

public class RollFileServiceImpl extends BaseServiceImpl<RollFile> implements
		RollFileService {
	private RollFileDao dao;

	public RollFileServiceImpl(RollFileDao dao) {
		super(dao);
		this.dao = dao;
	}
}
