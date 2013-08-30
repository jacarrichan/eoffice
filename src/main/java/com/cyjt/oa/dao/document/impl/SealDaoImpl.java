package com.cyjt.oa.dao.document.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.document.SealDao;
import com.cyjt.oa.model.document.Seal;

public class SealDaoImpl extends BaseDaoImpl<Seal> implements SealDao {
	public SealDaoImpl() {
		super(Seal.class);
	}
}
