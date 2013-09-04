package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.AssetsTypeDao;
import com.palmelf.eoffice.model.admin.AssetsType;
import com.palmelf.eoffice.service.admin.AssetsTypeService;

public class AssetsTypeServiceImpl extends BaseServiceImpl<AssetsType>
		implements AssetsTypeService {
	private AssetsTypeDao dao;

	public AssetsTypeServiceImpl(AssetsTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
