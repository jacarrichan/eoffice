package com.palmelf.eoffice.dao.arch.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.arch.BorrowFileListDao;
import com.palmelf.eoffice.model.arch.BorrowFileList;

public class BorrowFileListDaoImpl extends BaseDaoImpl<BorrowFileList>
		implements BorrowFileListDao {
	public BorrowFileListDaoImpl() {
		super(BorrowFileList.class);
	}
}
