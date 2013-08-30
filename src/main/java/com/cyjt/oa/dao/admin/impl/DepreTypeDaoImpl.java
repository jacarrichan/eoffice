package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.DepreTypeDao;
import com.cyjt.oa.model.admin.DepreType;

public class DepreTypeDaoImpl extends BaseDaoImpl<DepreType> implements
		DepreTypeDao {
	public DepreTypeDaoImpl() {
		super(DepreType.class);
	}
}
