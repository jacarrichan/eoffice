package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.OfficeGoodsTypeDao;
import com.palmelf.eoffice.model.admin.OfficeGoodsType;
import com.palmelf.eoffice.service.admin.OfficeGoodsTypeService;

public class OfficeGoodsTypeServiceImpl extends
		BaseServiceImpl<OfficeGoodsType> implements OfficeGoodsTypeService {
	private OfficeGoodsTypeDao dao;

	public OfficeGoodsTypeServiceImpl(OfficeGoodsTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
