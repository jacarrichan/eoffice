package com.palmelf.eoffice.dao.communicate.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.communicate.MobileMsgDao;
import com.palmelf.eoffice.model.communicate.MobileMsg;

public class MobileMsgDaoImpl extends BaseDaoImpl<MobileMsg> implements
		MobileMsgDao {
	public MobileMsgDaoImpl() {
		super(MobileMsg.class);
	}
}
