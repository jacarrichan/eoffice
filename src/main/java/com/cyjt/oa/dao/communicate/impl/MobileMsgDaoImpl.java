package com.cyjt.oa.dao.communicate.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.communicate.MobileMsgDao;
import com.cyjt.oa.model.communicate.MobileMsg;

public class MobileMsgDaoImpl extends BaseDaoImpl<MobileMsg> implements
		MobileMsgDao {
	public MobileMsgDaoImpl() {
		super(MobileMsg.class);
	}
}
