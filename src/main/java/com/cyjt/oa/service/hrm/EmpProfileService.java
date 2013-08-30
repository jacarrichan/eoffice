package com.cyjt.oa.service.hrm;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.hrm.EmpProfile;

public abstract interface EmpProfileService extends BaseService<EmpProfile> {
	public abstract boolean checkProfileNo(String paramString);
}
