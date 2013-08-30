package com.cyjt.oa.service.personal.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.personal.DutySectionDao;
import com.cyjt.oa.model.personal.DutySection;
import com.cyjt.oa.service.personal.DutySectionService;

public class DutySectionServiceImpl extends BaseServiceImpl<DutySection>
		implements DutySectionService {
	private DutySectionDao dao;

	public DutySectionServiceImpl(DutySectionDao dao) {
		super(dao);
		this.dao = dao;
	}
}
