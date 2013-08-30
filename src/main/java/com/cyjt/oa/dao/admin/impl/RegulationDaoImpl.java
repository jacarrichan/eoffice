package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.RegulationDao;
import com.cyjt.oa.model.admin.Regulation;

public class RegulationDaoImpl extends BaseDaoImpl<Regulation> implements
		RegulationDao {
	public RegulationDaoImpl() {
		super(Regulation.class);
	}
}
