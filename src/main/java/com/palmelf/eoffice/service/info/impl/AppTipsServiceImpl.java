package com.palmelf.eoffice.service.info.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.info.AppTipsDao;
import com.palmelf.eoffice.model.info.AppTips;
import com.palmelf.eoffice.service.info.AppTipsService;

public class AppTipsServiceImpl extends BaseServiceImpl<AppTips> implements
		AppTipsService {
	private AppTipsDao dao;

	public AppTipsServiceImpl(AppTipsDao dao) {
		super(dao);
		this.dao = dao;
	}
}
