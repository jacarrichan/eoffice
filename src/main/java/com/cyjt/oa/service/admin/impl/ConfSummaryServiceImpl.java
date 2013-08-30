package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.ConfSummaryDao;
import com.cyjt.oa.model.admin.ConfSummary;
import com.cyjt.oa.service.admin.ConfSummaryService;

public class ConfSummaryServiceImpl extends BaseServiceImpl<ConfSummary>
		implements ConfSummaryService {
	private ConfSummaryDao dao;

	public ConfSummaryServiceImpl(ConfSummaryDao dao) {
		super(dao);
		this.dao = dao;
	}

	public ConfSummary send(ConfSummary cm, String fileIds) {
		return this.dao.send(cm, fileIds);
	}

	public ConfSummary save(ConfSummary cm, String fileIds) {
		return this.dao.save(cm, fileIds);
	}
}
