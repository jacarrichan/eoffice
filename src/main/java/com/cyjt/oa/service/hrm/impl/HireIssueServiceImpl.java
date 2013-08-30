package com.cyjt.oa.service.hrm.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.hrm.HireIssueDao;
import com.cyjt.oa.model.hrm.HireIssue;
import com.cyjt.oa.service.hrm.HireIssueService;

public class HireIssueServiceImpl extends BaseServiceImpl<HireIssue> implements
		HireIssueService {
	private HireIssueDao dao;

	public HireIssueServiceImpl(HireIssueDao dao) {
		super(dao);
		this.dao = dao;
	}
}
