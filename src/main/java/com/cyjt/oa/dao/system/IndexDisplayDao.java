package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.system.IndexDisplay;
import java.util.List;

public abstract interface IndexDisplayDao extends BaseDao<IndexDisplay> {
	public abstract List<IndexDisplay> findByUser(Long paramLong);
}
