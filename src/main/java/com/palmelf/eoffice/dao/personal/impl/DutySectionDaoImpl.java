package com.palmelf.eoffice.dao.personal.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.personal.DutySectionDao;
import com.palmelf.eoffice.model.personal.DutySection;

public class DutySectionDaoImpl extends BaseDaoImpl<DutySection> implements
		DutySectionDao {
	public DutySectionDaoImpl() {
		super(DutySection.class);
	}
}
