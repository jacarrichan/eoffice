package com.palmelf.eoffice.dao.admin;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.admin.ConfSummary;

public abstract interface ConfSummaryDao extends BaseDao<ConfSummary> {
	public abstract ConfSummary send(ConfSummary paramConfSummary,
			String paramString);

	public abstract ConfSummary save(ConfSummary paramConfSummary,
			String paramString);
}
