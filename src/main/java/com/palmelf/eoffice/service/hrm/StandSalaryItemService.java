package com.palmelf.eoffice.service.hrm;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.hrm.StandSalaryItem;

import java.util.List;

public abstract interface StandSalaryItemService extends
		BaseService<StandSalaryItem> {
	public abstract List<StandSalaryItem> getAllByStandardId(Long paramLong);
}
