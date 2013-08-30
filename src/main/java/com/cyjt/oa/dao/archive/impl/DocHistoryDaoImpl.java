package com.cyjt.oa.dao.archive.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.archive.DocHistoryDao;
import com.cyjt.oa.model.archive.DocHistory;

public class DocHistoryDaoImpl extends BaseDaoImpl<DocHistory> implements
		DocHistoryDao {
	public DocHistoryDaoImpl() {
		super(DocHistory.class);
	}
}
