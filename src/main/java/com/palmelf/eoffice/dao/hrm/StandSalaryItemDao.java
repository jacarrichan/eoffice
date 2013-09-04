package com.palmelf.eoffice.dao.hrm;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.hrm.StandSalaryItem;

import java.util.List;

public abstract interface StandSalaryItemDao extends BaseDao<StandSalaryItem> {
	public abstract List<StandSalaryItem> getAllByStandardId(Long paramLong);
}
