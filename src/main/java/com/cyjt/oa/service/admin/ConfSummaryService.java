package com.cyjt.oa.service.admin;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.admin.ConfSummary;

public abstract interface ConfSummaryService extends BaseService<ConfSummary> {
	public abstract ConfSummary send(ConfSummary paramConfSummary,
			String paramString);

	public abstract ConfSummary save(ConfSummary paramConfSummary,
			String paramString);
}
