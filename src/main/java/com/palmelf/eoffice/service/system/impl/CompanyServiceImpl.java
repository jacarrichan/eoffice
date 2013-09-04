package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.system.CompanyDao;
import com.palmelf.eoffice.model.system.Company;
import com.palmelf.eoffice.service.system.CompanyService;

import java.util.List;

public class CompanyServiceImpl extends BaseServiceImpl<Company> implements
		CompanyService {
	private CompanyDao companyDao;

	public CompanyServiceImpl(CompanyDao companyDao) {
		super(companyDao);
		this.companyDao = companyDao;
	}

	public List<Company> findCompany() {
		return this.companyDao.findCompany();
	}

	public List<Company> findByHql(String hql) {
		return null;
	}
}
