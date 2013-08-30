package com.cyjt.oa.dao.system.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.system.IndexDisplayDao;
import com.cyjt.oa.model.system.IndexDisplay;
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
