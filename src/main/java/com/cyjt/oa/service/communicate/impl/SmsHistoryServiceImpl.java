package com.cyjt.oa.service.communicate.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.communicate.SmsHistoryDao;
import com.cyjt.oa.model.communicate.SmsHistory;
import com.cyjt.oa.service.communicate.SmsHistoryService;

public class SmsHistoryServiceImpl extends BaseServiceImpl<SmsHistory> implements SmsHistoryService {
	private SmsHistoryDao dao;

	public SmsHistoryServiceImpl(SmsHistoryDao dao) {
		super(dao);
		this.dao = dao;
	}
}