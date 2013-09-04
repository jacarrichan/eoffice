package com.palmelf.eoffice.dao.document.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.document.SealDao;
import com.palmelf.eoffice.model.document.Seal;

public class SealDaoImpl extends BaseDaoImpl<Seal> implements SealDao {
	public SealDaoImpl() {
		super(Seal.class);
	}
}
