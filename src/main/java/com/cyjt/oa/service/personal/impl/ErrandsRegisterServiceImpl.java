package com.cyjt.oa.service.personal.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.personal.ErrandsRegisterDao;
import com.cyjt.oa.model.personal.ErrandsRegister;
import com.cyjt.oa.service.personal.ErrandsRegisterService;

public class ErrandsRegisterServiceImpl extends
		BaseServiceImpl<ErrandsRegister> implements ErrandsRegisterService {
	private ErrandsRegisterDao dao;

	public ErrandsRegisterServiceImpl(ErrandsRegisterDao dao) {
		super(dao);
		this.dao = dao;
	}
}
