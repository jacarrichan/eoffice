package com.cyjt.oa.dao.communicate;

import java.util.List;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.communicate.SmsMobile;

public abstract interface SmsMobileDao extends BaseDao<SmsMobile> {
	public abstract List<SmsMobile> getNeedToSend();
}