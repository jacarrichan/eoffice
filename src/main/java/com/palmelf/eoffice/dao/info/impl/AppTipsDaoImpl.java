package com.palmelf.eoffice.dao.info.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.info.AppTipsDao;
import com.palmelf.eoffice.model.info.AppTips;

public class AppTipsDaoImpl extends BaseDaoImpl<AppTips> implements AppTipsDao {
	public AppTipsDaoImpl() {
		super(AppTips.class);
	}
}
