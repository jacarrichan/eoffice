package com.cyjt.oa.dao.communicate.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.communicate.SmsMobileDao;
import com.cyjt.oa.model.communicate.SmsMobile;
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