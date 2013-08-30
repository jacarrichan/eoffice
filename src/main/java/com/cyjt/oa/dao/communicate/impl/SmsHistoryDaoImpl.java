package com.cyjt.oa.dao.communicate.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.communicate.SmsHistoryDao;
import com.cyjt.oa.model.communicate.SmsHistory;

public class SmsHistoryDaoImpl extends BaseDaoImpl<SmsHistory> implements SmsHistoryDao {
	public SmsHistoryDaoImpl() {
		super(SmsHistory.class);
	}
}