package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.DepreTypeDao;
import com.palmelf.eoffice.model.admin.DepreType;
import com.palmelf.eoffice.service.admin.DepreTypeService;

public class DepreTypeServiceImpl extends BaseServiceImpl<DepreType> implements
		DepreTypeService {
	private DepreTypeDao dao;

	public DepreTypeServiceImpl(DepreTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
