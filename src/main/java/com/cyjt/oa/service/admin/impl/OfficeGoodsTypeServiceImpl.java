package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.OfficeGoodsTypeDao;
import com.cyjt.oa.model.admin.OfficeGoodsType;
import com.cyjt.oa.service.admin.OfficeGoodsTypeService;

public class OfficeGoodsTypeServiceImpl extends
		BaseServiceImpl<OfficeGoodsType> implements OfficeGoodsTypeService {
	private OfficeGoodsTypeDao dao;

	public OfficeGoodsTypeServiceImpl(OfficeGoodsTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
