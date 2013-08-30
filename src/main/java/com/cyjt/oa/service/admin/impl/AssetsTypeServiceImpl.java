package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.AssetsTypeDao;
import com.cyjt.oa.model.admin.AssetsType;
import com.cyjt.oa.service.admin.AssetsTypeService;

public class AssetsTypeServiceImpl extends BaseServiceImpl<AssetsType>
		implements AssetsTypeService {
	private AssetsTypeDao dao;

	public AssetsTypeServiceImpl(AssetsTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
