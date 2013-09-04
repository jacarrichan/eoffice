package com.palmelf.eoffice.service.arch.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.arch.BorrowFileListDao;
import com.palmelf.eoffice.model.arch.BorrowFileList;
import com.palmelf.eoffice.service.arch.BorrowFileListService;

public class BorrowFileListServiceImpl extends BaseServiceImpl<BorrowFileList>
		implements BorrowFileListService {
	private BorrowFileListDao dao;

	public BorrowFileListServiceImpl(BorrowFileListDao dao) {
		super(dao);
		this.dao = dao;
	}
}
