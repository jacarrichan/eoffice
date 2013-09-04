package com.palmelf.eoffice.service.admin;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.admin.ConfSummary;

public abstract interface ConfSummaryService extends BaseService<ConfSummary> {
	public abstract ConfSummary send(ConfSummary paramConfSummary,
			String paramString);

	public abstract ConfSummary save(ConfSummary paramConfSummary,
			String paramString);
}
