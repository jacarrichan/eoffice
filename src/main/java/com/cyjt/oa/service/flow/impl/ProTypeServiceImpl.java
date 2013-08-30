package com.cyjt.oa.service.flow.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.flow.ProTypeDao;
import com.cyjt.oa.model.flow.ProType;
import com.cyjt.oa.service.flow.ProTypeService;

public class ProTypeServiceImpl extends BaseServiceImpl<ProType> implements
		ProTypeService {
	private ProTypeDao dao;

	public ProTypeServiceImpl(ProTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
