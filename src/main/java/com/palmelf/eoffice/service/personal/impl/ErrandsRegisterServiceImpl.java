package com.palmelf.eoffice.service.personal.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.personal.ErrandsRegisterDao;
import com.palmelf.eoffice.model.personal.ErrandsRegister;
import com.palmelf.eoffice.service.personal.ErrandsRegisterService;

public class ErrandsRegisterServiceImpl extends
		BaseServiceImpl<ErrandsRegister> implements ErrandsRegisterService {
	private ErrandsRegisterDao dao;

	public ErrandsRegisterServiceImpl(ErrandsRegisterDao dao) {
		super(dao);
		this.dao = dao;
	}
}
