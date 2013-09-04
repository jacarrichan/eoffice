package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.FixedAssetsDao;
import com.palmelf.eoffice.model.admin.FixedAssets;

public class FixedAssetsDaoImpl extends BaseDaoImpl<FixedAssets> implements
		FixedAssetsDao {
	public FixedAssetsDaoImpl() {
		super(FixedAssets.class);
	}
}
