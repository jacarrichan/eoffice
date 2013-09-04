package com.palmelf.eoffice.dao.hrm;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.hrm.EmpProfile;

public abstract interface EmpProfileDao extends BaseDao<EmpProfile> {
	public abstract boolean checkProfileNo(String paramString);
}
