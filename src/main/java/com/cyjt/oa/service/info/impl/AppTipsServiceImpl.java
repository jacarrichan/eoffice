package com.cyjt.oa.service.info.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.info.AppTipsDao;
import com.cyjt.oa.model.info.AppTips;
import com.cyjt.oa.service.info.AppTipsService;

public class AppTipsServiceImpl extends BaseServiceImpl<AppTips> implements
		AppTipsService {
	private AppTipsDao dao;

	public AppTipsServiceImpl(AppTipsDao dao) {
		super(dao);
		this.dao = dao;
	}
}
