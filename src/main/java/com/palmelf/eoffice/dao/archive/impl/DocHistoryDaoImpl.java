package com.palmelf.eoffice.dao.archive.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.archive.DocHistoryDao;
import com.palmelf.eoffice.model.archive.DocHistory;

public class DocHistoryDaoImpl extends BaseDaoImpl<DocHistory> implements
		DocHistoryDao {
	public DocHistoryDaoImpl() {
		super(DocHistory.class);
	}
}
