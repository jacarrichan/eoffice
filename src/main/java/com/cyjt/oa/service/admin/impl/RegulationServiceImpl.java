package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.RegulationDao;
import com.cyjt.oa.model.admin.Regulation;
import com.cyjt.oa.service.admin.RegulationService;

public class RegulationServiceImpl extends BaseServiceImpl<Regulation>
		implements RegulationService {
	private RegulationDao dao;

	public RegulationServiceImpl(RegulationDao dao) {
		super(dao);
		this.dao = dao;
	}
}
