package com.cyjt.oa.dao.admin;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.admin.ConfSummary;

public abstract interface ConfSummaryDao extends BaseDao<ConfSummary> {
	public abstract ConfSummary send(ConfSummary paramConfSummary,
			String paramString);

	public abstract ConfSummary save(ConfSummary paramConfSummary,
			String paramString);
}
