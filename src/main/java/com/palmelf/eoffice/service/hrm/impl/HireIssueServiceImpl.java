package com.palmelf.eoffice.service.hrm.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.hrm.HireIssueDao;
import com.palmelf.eoffice.model.hrm.HireIssue;
import com.palmelf.eoffice.service.hrm.HireIssueService;

public class HireIssueServiceImpl extends BaseServiceImpl<HireIssue> implements
		HireIssueService {
	private HireIssueDao dao;

	public HireIssueServiceImpl(HireIssueDao dao) {
		super(dao);
		this.dao = dao;
	}
}
