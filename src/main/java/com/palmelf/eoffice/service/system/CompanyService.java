package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.system.Company;

import java.util.List;

public abstract interface CompanyService extends BaseService<Company> {
	public abstract List<Company> findByHql(String paramString);

	public abstract List<Company> findCompany();
}
