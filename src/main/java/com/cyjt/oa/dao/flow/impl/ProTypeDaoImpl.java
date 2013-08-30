package com.cyjt.oa.dao.flow.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.flow.ProTypeDao;
import com.cyjt.oa.model.flow.ProType;

public class ProTypeDaoImpl extends BaseDaoImpl<ProType> implements ProTypeDao {
	public ProTypeDaoImpl() {
		super(ProType.class);
	}
}
