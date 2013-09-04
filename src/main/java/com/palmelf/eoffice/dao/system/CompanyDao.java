package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.system.Company;

import java.util.List;

public abstract interface CompanyDao extends BaseDao<Company> {
	public abstract List<Company> findByHql(String paramString);

	public abstract List<Company> findCompany();
}
