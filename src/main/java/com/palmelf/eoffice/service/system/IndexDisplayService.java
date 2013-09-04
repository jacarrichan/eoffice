package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.system.IndexDisplay;

import java.util.List;

public abstract interface IndexDisplayService extends BaseService<IndexDisplay> {
	public abstract List<IndexDisplay> findByUser(Long paramLong);
}
