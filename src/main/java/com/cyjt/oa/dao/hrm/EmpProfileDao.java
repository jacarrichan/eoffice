package com.cyjt.oa.dao.hrm;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.hrm.EmpProfile;

public abstract interface EmpProfileDao extends BaseDao<EmpProfile> {
	public abstract boolean checkProfileNo(String paramString);
}
