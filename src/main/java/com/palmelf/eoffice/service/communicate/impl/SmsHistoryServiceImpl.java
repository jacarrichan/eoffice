package com.palmelf.eoffice.service.communicate.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.communicate.SmsHistoryDao;
import com.palmelf.eoffice.model.communicate.SmsHistory;
import com.palmelf.eoffice.service.communicate.SmsHistoryService;

public class SmsHistoryServiceImpl extends BaseServiceImpl<SmsHistory> implements SmsHistoryService {
	private SmsHistoryDao dao;

	public SmsHistoryServiceImpl(SmsHistoryDao dao) {
		super(dao);
		this.dao = dao;
	}
}