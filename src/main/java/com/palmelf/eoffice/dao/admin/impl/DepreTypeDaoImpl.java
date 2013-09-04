package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.DepreTypeDao;
import com.palmelf.eoffice.model.admin.DepreType;

public class DepreTypeDaoImpl extends BaseDaoImpl<DepreType> implements
		DepreTypeDao {
	public DepreTypeDaoImpl() {
		super(DepreType.class);
	}
}
