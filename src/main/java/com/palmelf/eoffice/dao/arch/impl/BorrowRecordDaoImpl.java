package com.palmelf.eoffice.dao.arch.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.arch.BorrowRecordDao;
import com.palmelf.eoffice.model.arch.BorrowRecord;

public class BorrowRecordDaoImpl extends BaseDaoImpl<BorrowRecord> implements
		BorrowRecordDao {
	public BorrowRecordDaoImpl() {
		super(BorrowRecord.class);
	}
}
