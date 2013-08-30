package com.cyjt.oa.service.system.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.system.CompanyDao;
import com.cyjt.oa.model.system.Company;
import com.cyjt.oa.service.system.CompanyService;
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
