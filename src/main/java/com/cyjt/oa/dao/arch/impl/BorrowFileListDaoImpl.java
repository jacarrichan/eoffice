package com.cyjt.oa.dao.arch.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.arch.BorrowFileListDao;
import com.cyjt.oa.model.arch.BorrowFileList;

public class BorrowFileListDaoImpl extends BaseDaoImpl<BorrowFileList>
		implements BorrowFileListDao {
	public BorrowFileListDaoImpl() {
		super(BorrowFileList.class);
	}
}
