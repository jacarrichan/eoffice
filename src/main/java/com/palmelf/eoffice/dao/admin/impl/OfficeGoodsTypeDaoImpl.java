package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.OfficeGoodsTypeDao;
import com.palmelf.eoffice.model.admin.OfficeGoodsType;

public class OfficeGoodsTypeDaoImpl extends BaseDaoImpl<OfficeGoodsType>
		implements OfficeGoodsTypeDao {
	public OfficeGoodsTypeDaoImpl() {
		super(OfficeGoodsType.class);
	}
}
