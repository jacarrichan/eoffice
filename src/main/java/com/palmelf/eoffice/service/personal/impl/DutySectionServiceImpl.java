package com.palmelf.eoffice.service.personal.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.personal.DutySectionDao;
import com.palmelf.eoffice.model.personal.DutySection;
import com.palmelf.eoffice.service.personal.DutySectionService;

public class DutySectionServiceImpl extends BaseServiceImpl<DutySection>
		implements DutySectionService {
	private DutySectionDao dao;

	public DutySectionServiceImpl(DutySectionDao dao) {
		super(dao);
		this.dao = dao;
	}
}
