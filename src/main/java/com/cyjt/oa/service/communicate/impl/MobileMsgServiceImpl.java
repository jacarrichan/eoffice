package com.cyjt.oa.service.communicate.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.communicate.MobileMsgDao;
import com.cyjt.oa.model.communicate.MobileMsg;
import com.cyjt.oa.service.communicate.MobileMsgService;

public class MobileMsgServiceImpl extends BaseServiceImpl<MobileMsg> implements
		MobileMsgService {
	private MobileMsgDao dao;

	public MobileMsgServiceImpl(MobileMsgDao dao) {
		super(dao);
		this.dao = dao;
	}
}
