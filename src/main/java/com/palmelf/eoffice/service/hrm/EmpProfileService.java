package com.palmelf.eoffice.service.hrm;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.hrm.EmpProfile;

public abstract interface EmpProfileService extends BaseService<EmpProfile> {
	public abstract boolean checkProfileNo(String paramString);
}
