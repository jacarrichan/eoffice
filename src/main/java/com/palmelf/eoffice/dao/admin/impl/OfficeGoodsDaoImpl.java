package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.OfficeGoodsDao;
import com.palmelf.eoffice.model.admin.OfficeGoods;

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
