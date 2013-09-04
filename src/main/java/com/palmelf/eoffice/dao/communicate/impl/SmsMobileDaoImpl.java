package com.palmelf.eoffice.dao.communicate.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.communicate.SmsMobileDao;
import com.palmelf.eoffice.model.communicate.SmsMobile;

import java.util.List;

public class SmsMobileDaoImpl extends BaseDaoImpl<SmsMobile> implements SmsMobileDao {
	public SmsMobileDaoImpl() {
		super(SmsMobile.class);
	}

	public List<SmsMobile> getNeedToSend() {
		String hql = "from SmsMobile sm where sm.status = ? order by sm.sendTime desc";
		Object[] params = { SmsMobile.STATUS_NOT_SENDED };
		return findByHql(hql, params);
	}
}