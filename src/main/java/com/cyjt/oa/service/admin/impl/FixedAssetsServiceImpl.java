package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.FixedAssetsDao;
import com.cyjt.oa.model.admin.FixedAssets;
import com.cyjt.oa.service.admin.FixedAssetsService;

public class FixedAssetsServiceImpl extends BaseServiceImpl<FixedAssets>
		implements FixedAssetsService {
	private FixedAssetsDao dao;

	public FixedAssetsServiceImpl(FixedAssetsDao dao) {
		super(dao);
		this.dao = dao;
	}
}
