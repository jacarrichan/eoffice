package com.palmelf.eoffice.dao.admin;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.admin.OfficeGoods;

import java.util.List;

public abstract interface OfficeGoodsDao extends BaseDao<OfficeGoods> {
	public abstract List<OfficeGoods> findByWarm();
}
