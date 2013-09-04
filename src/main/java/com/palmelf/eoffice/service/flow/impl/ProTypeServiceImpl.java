package com.palmelf.eoffice.service.flow.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.flow.ProTypeDao;
import com.palmelf.eoffice.model.flow.ProType;
import com.palmelf.eoffice.service.flow.ProTypeService;

public class ProTypeServiceImpl extends BaseServiceImpl<ProType> implements
		ProTypeService {
	private ProTypeDao dao;

	public ProTypeServiceImpl(ProTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
