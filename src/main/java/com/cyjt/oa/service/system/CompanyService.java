package com.cyjt.oa.service.system;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.system.Company;
import java.util.List;

public abstract interface CompanyService extends BaseService<Company> {
	public abstract List<Company> findByHql(String paramString);

	public abstract List<Company> findCompany();
}
