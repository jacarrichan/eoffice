package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.RegulationDao;
import com.palmelf.eoffice.model.admin.Regulation;

public class RegulationDaoImpl extends BaseDaoImpl<Regulation> implements
		RegulationDao {
	public RegulationDaoImpl() {
		super(Regulation.class);
	}
}
