package com.palmelf.eoffice.dao.communicate;

import java.util.List;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.communicate.SmsMobile;

public abstract interface SmsMobileDao extends BaseDao<SmsMobile> {
	public abstract List<SmsMobile> getNeedToSend();
}