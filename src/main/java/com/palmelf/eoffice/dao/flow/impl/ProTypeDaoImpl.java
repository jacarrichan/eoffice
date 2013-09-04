package com.palmelf.eoffice.dao.flow.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.flow.ProTypeDao;
import com.palmelf.eoffice.model.flow.ProType;

public class ProTypeDaoImpl extends BaseDaoImpl<ProType> implements ProTypeDao {
	public ProTypeDaoImpl() {
		super(ProType.class);
	}
}
