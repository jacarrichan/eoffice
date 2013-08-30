package com.cyjt.oa.dao.admin;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.admin.OfficeGoods;
import java.util.List;

public abstract interface OfficeGoodsDao extends BaseDao<OfficeGoods> {
	public abstract List<OfficeGoods> findByWarm();
}
