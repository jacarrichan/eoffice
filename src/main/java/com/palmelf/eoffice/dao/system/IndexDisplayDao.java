package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.system.IndexDisplay;

import java.util.List;

public abstract interface IndexDisplayDao extends BaseDao<IndexDisplay> {
	public abstract List<IndexDisplay> findByUser(Long paramLong);
}
