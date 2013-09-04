package com.palmelf.eoffice.dao.communicate.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.communicate.SmsHistoryDao;
import com.palmelf.eoffice.model.communicate.SmsHistory;

public class SmsHistoryDaoImpl extends BaseDaoImpl<SmsHistory> implements SmsHistoryDao {
	public SmsHistoryDaoImpl() {
		super(SmsHistory.class);
	}
}