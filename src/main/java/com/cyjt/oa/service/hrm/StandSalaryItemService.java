package com.cyjt.oa.service.hrm;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.hrm.StandSalaryItem;
import java.util.List;

public abstract interface StandSalaryItemService extends
		BaseService<StandSalaryItem> {
	public abstract List<StandSalaryItem> getAllByStandardId(Long paramLong);
}
