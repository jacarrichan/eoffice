package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.FixedAssetsDao;
import com.cyjt.oa.model.admin.FixedAssets;

public class FixedAssetsDaoImpl extends BaseDaoImpl<FixedAssets> implements
		FixedAssetsDao {
	public FixedAssetsDaoImpl() {
		super(FixedAssets.class);
	}
}
