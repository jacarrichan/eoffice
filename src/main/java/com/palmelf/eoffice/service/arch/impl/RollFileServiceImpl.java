package com.palmelf.eoffice.service.arch.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.arch.RollFileDao;
import com.palmelf.eoffice.model.arch.RollFile;
import com.palmelf.eoffice.service.arch.RollFileService;

public class RollFileServiceImpl extends BaseServiceImpl<RollFile> implements
		RollFileService {
	private RollFileDao dao;

	public RollFileServiceImpl(RollFileDao dao) {
		super(dao);
		this.dao = dao;
	}
}
