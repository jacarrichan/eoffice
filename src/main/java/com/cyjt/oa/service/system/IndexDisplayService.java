package com.cyjt.oa.service.system;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.system.IndexDisplay;
import java.util.List;

public abstract interface IndexDisplayService extends BaseService<IndexDisplay> {
	public abstract List<IndexDisplay> findByUser(Long paramLong);
}
