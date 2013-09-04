package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.ConfSummaryDao;
import com.palmelf.eoffice.model.admin.ConfSummary;
import com.palmelf.eoffice.service.admin.ConfSummaryService;

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
