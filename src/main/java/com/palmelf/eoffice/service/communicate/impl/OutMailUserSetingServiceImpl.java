package com.palmelf.eoffice.service.communicate.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.communicate.OutMailUserSetingDao;
import com.palmelf.eoffice.model.communicate.OutMailUserSeting;
import com.palmelf.eoffice.service.communicate.OutMailUserSetingService;

public class OutMailUserSetingServiceImpl extends
		BaseServiceImpl<OutMailUserSeting> implements OutMailUserSetingService {
	private OutMailUserSetingDao dao;

	public OutMailUserSetingServiceImpl(OutMailUserSetingDao dao) {
		super(dao);
		this.dao = dao;
	}

	public OutMailUserSeting getByLoginId(Long loginid) {
		return this.dao.getByLoginId(loginid);
	}
}
