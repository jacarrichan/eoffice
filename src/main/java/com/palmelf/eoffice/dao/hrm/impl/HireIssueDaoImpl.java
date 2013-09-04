package com.palmelf.eoffice.dao.hrm.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.hrm.HireIssueDao;
import com.palmelf.eoffice.model.hrm.HireIssue;

public class HireIssueDaoImpl extends BaseDaoImpl<HireIssue> implements
		HireIssueDao {
	public HireIssueDaoImpl() {
		super(HireIssue.class);
	}
}
