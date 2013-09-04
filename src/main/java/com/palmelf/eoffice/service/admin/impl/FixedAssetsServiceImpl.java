package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.FixedAssetsDao;
import com.palmelf.eoffice.model.admin.FixedAssets;
import com.palmelf.eoffice.service.admin.FixedAssetsService;

public class FixedAssetsServiceImpl extends BaseServiceImpl<FixedAssets>
		implements FixedAssetsService {
	private FixedAssetsDao dao;

	public FixedAssetsServiceImpl(FixedAssetsDao dao) {
		super(dao);
		this.dao = dao;
	}
}
