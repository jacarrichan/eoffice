package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.system.IndexDisplayDao;
import com.palmelf.eoffice.model.system.IndexDisplay;
import com.palmelf.eoffice.service.system.IndexDisplayService;

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
