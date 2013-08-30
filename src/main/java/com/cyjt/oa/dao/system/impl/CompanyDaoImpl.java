package com.cyjt.oa.dao.system.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.system.CompanyDao;
import com.cyjt.oa.model.system.Company;
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
