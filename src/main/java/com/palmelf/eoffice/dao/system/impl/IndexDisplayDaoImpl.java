package com.palmelf.eoffice.dao.system.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.system.IndexDisplayDao;
import com.palmelf.eoffice.model.system.IndexDisplay;

import java.util.List;

public class IndexDisplayDaoImpl extends BaseDaoImpl<IndexDisplay> implements
		IndexDisplayDao {
	public IndexDisplayDaoImpl() {
		super(IndexDisplay.class);
	}

	public List<IndexDisplay> findByUser(Long userId) {
		String hql = "from IndexDisplay vo where vo.appUser.userId=?";
		return findByHql(hql, new Object[] { userId });
	}
}
