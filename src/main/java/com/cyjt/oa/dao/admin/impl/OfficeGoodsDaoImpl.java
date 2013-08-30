package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.OfficeGoodsDao;
import com.cyjt.oa.model.admin.OfficeGoods;
import java.util.List;

public class OfficeGoodsDaoImpl extends BaseDaoImpl<OfficeGoods> implements
		OfficeGoodsDao {
	public OfficeGoodsDaoImpl() {
		super(OfficeGoods.class);
	}

	public List<OfficeGoods> findByWarm() {
		String hql = "from OfficeGoods vo where ((vo.stockCounts<=vo.warnCounts and vo.isWarning=1) or (vo.stockCounts<=0))";
		return findByHql(hql);
	}
}
