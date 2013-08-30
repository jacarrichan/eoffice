package com.cyjt.oa.dao.personal.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.personal.DutySectionDao;
import com.cyjt.oa.model.personal.DutySection;

public class DutySectionDaoImpl extends BaseDaoImpl<DutySection> implements
		DutySectionDao {
	public DutySectionDaoImpl() {
		super(DutySection.class);
	}
}
