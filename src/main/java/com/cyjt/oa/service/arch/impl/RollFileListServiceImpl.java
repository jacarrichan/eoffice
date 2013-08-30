package com.cyjt.oa.service.arch.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.arch.RollFileListDao;
import com.cyjt.oa.model.arch.RollFileList;
import com.cyjt.oa.service.arch.RollFileListService;

public class RollFileListServiceImpl extends BaseServiceImpl<RollFileList>
		implements RollFileListService {
	private RollFileListDao dao;

	public RollFileListServiceImpl(RollFileListDao dao) {
		super(dao);
		this.dao = dao;
	}
}
