package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.DepreTypeDao;
import com.cyjt.oa.model.admin.DepreType;
import com.cyjt.oa.service.admin.DepreTypeService;

public class DepreTypeServiceImpl extends BaseServiceImpl<DepreType> implements
		DepreTypeService {
	private DepreTypeDao dao;

	public DepreTypeServiceImpl(DepreTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
