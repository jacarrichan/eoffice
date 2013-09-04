package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.GoodsApplyDao;
import com.palmelf.eoffice.model.admin.GoodsApply;

public class GoodsApplyDaoImpl extends BaseDaoImpl<GoodsApply> implements
		GoodsApplyDao {
	public GoodsApplyDaoImpl() {
		super(GoodsApply.class);
	}
}
