package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.system.Company;
import java.util.List;

public abstract interface CompanyDao extends BaseDao<Company> {
	public abstract List<Company> findByHql(String paramString);

	public abstract List<Company> findCompany();
}
