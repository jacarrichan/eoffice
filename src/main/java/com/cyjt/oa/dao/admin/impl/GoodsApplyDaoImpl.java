package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.GoodsApplyDao;
import com.cyjt.oa.model.admin.GoodsApply;

public class GoodsApplyDaoImpl extends BaseDaoImpl<GoodsApply> implements
		GoodsApplyDao {
	public GoodsApplyDaoImpl() {
		super(GoodsApply.class);
	}
}
