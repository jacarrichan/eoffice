package com.palmelf.eoffice.dao.system.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.system.CompanyDao;
import com.palmelf.eoffice.model.system.Company;

import java.util.List;

public class CompanyDaoImpl extends BaseDaoImpl<Company> implements CompanyDao {
	public CompanyDaoImpl() {
		super(Company.class);
	}

	public List<Company> findCompany() {
		String hql = "from Company c";
		return findByHql(hql);
	}
}
