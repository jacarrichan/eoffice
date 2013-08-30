package com.cyjt.oa.service.arch.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.arch.BorrowFileListDao;
import com.cyjt.oa.model.arch.BorrowFileList;
import com.cyjt.oa.service.arch.BorrowFileListService;

public class BorrowFileListServiceImpl extends BaseServiceImpl<BorrowFileList>
		implements BorrowFileListService {
	private BorrowFileListDao dao;

	public BorrowFileListServiceImpl(BorrowFileListDao dao) {
		super(dao);
		this.dao = dao;
	}
}
