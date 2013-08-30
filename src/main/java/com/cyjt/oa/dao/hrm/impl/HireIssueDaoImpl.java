package com.cyjt.oa.dao.hrm.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.hrm.HireIssueDao;
import com.cyjt.oa.model.hrm.HireIssue;

public class HireIssueDaoImpl extends BaseDaoImpl<HireIssue> implements
		HireIssueDao {
	public HireIssueDaoImpl() {
		super(HireIssue.class);
	}
}
