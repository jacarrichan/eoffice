package com.cyjt.oa.dao.arch.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.arch.BorrowRecordDao;
import com.cyjt.oa.model.arch.BorrowRecord;

public class BorrowRecordDaoImpl extends BaseDaoImpl<BorrowRecord> implements
		BorrowRecordDao {
	public BorrowRecordDaoImpl() {
		super(BorrowRecord.class);
	}
}
