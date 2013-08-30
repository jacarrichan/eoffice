package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.archive.DocHistoryDao;
import com.cyjt.oa.model.archive.DocHistory;
import com.cyjt.oa.service.archive.DocHistoryService;

public class DocHistoryServiceImpl extends BaseServiceImpl<DocHistory>
		implements DocHistoryService {
	private DocHistoryDao dao;

	public DocHistoryServiceImpl(DocHistoryDao dao) {
		super(dao);
		this.dao = dao;
	}
}
