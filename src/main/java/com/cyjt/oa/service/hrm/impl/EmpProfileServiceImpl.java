package com.cyjt.oa.service.hrm.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.hrm.EmpProfileDao;
import com.cyjt.oa.model.hrm.EmpProfile;
import com.cyjt.oa.service.hrm.EmpProfileService;

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
