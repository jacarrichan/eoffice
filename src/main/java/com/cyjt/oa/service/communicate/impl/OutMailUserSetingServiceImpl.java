package com.cyjt.oa.service.communicate.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.communicate.OutMailUserSetingDao;
import com.cyjt.oa.model.communicate.OutMailUserSeting;
import com.cyjt.oa.service.communicate.OutMailUserSetingService;

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
