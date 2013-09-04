package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.RegulationDao;
import com.palmelf.eoffice.model.admin.Regulation;
import com.palmelf.eoffice.service.admin.RegulationService;

public class RegulationServiceImpl extends BaseServiceImpl<Regulation>
		implements RegulationService {
	private RegulationDao dao;

	public RegulationServiceImpl(RegulationDao dao) {
		super(dao);
		this.dao = dao;
	}
}
