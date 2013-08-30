package com.cyjt.oa.dao.info.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.info.AppTipsDao;
import com.cyjt.oa.model.info.AppTips;

public class AppTipsDaoImpl extends BaseDaoImpl<AppTips> implements AppTipsDao {
	public AppTipsDaoImpl() {
		super(AppTips.class);
	}
}
