package com.cyjt.oa.service.system.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.system.IndexDisplayDao;
import com.cyjt.oa.model.system.IndexDisplay;
import com.cyjt.oa.service.system.IndexDisplayService;
import java.util.List;

public class IndexDisplayServiceImpl extends BaseServiceImpl<IndexDisplay>
		implements IndexDisplayService {
	private IndexDisplayDao dao;

	public IndexDisplayServiceImpl(IndexDisplayDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<IndexDisplay> findByUser(Long userId) {
		return this.dao.findByUser(userId);
	}
}
