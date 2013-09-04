package com.palmelf.eoffice.service.arch.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.arch.BorrowRecordDao;
import com.palmelf.eoffice.model.arch.BorrowRecord;
import com.palmelf.eoffice.service.arch.BorrowRecordService;

public class BorrowRecordServiceImpl extends BaseServiceImpl<BorrowRecord>
		implements BorrowRecordService {
	private BorrowRecordDao dao;

	public BorrowRecordServiceImpl(BorrowRecordDao dao) {
		super(dao);
		this.dao = dao;
	}
}
