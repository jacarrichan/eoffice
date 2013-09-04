package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.archive.DocHistoryDao;
import com.palmelf.eoffice.model.archive.DocHistory;
import com.palmelf.eoffice.service.archive.DocHistoryService;

public class DocHistoryServiceImpl extends BaseServiceImpl<DocHistory>
		implements DocHistoryService {
	private DocHistoryDao dao;

	public DocHistoryServiceImpl(DocHistoryDao dao) {
		super(dao);
		this.dao = dao;
	}
}
