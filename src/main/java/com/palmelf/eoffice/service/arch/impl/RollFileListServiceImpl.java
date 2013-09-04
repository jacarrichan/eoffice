package com.palmelf.eoffice.service.arch.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.arch.RollFileListDao;
import com.palmelf.eoffice.model.arch.RollFileList;
import com.palmelf.eoffice.service.arch.RollFileListService;

public class RollFileListServiceImpl extends BaseServiceImpl<RollFileList>
		implements RollFileListService {
	private RollFileListDao dao;

	public RollFileListServiceImpl(RollFileListDao dao) {
		super(dao);
		this.dao = dao;
	}
}
