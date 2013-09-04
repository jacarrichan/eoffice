package com.palmelf.eoffice.service.communicate.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.communicate.MobileMsgDao;
import com.palmelf.eoffice.model.communicate.MobileMsg;
import com.palmelf.eoffice.service.communicate.MobileMsgService;

public class MobileMsgServiceImpl extends BaseServiceImpl<MobileMsg> implements
		MobileMsgService {
	private MobileMsgDao dao;

	public MobileMsgServiceImpl(MobileMsgDao dao) {
		super(dao);
		this.dao = dao;
	}
}
