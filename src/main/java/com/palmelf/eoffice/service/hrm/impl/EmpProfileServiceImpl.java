package com.palmelf.eoffice.service.hrm.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.hrm.EmpProfileDao;
import com.palmelf.eoffice.model.hrm.EmpProfile;
import com.palmelf.eoffice.service.hrm.EmpProfileService;

public class EmpProfileServiceImpl extends BaseServiceImpl<EmpProfile>
		implements EmpProfileService {
	private EmpProfileDao dao;

	public EmpProfileServiceImpl(EmpProfileDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean checkProfileNo(String profileNo) {
		return this.dao.checkProfileNo(profileNo);
	}
}
