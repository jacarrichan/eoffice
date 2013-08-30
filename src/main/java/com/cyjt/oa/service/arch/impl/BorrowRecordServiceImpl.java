package com.cyjt.oa.service.arch.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.arch.BorrowRecordDao;
import com.cyjt.oa.model.arch.BorrowRecord;
import com.cyjt.oa.service.arch.BorrowRecordService;

public class BorrowRecordServiceImpl extends BaseServiceImpl<BorrowRecord>
		implements BorrowRecordService {
	private BorrowRecordDao dao;

	public BorrowRecordServiceImpl(BorrowRecordDao dao) {
		super(dao);
		this.dao = dao;
	}
}
