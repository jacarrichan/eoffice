package com.cyjt.oa.dao.hrm;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.hrm.StandSalaryItem;
import java.util.List;

public abstract interface StandSalaryItemDao extends BaseDao<StandSalaryItem> {
	public abstract List<StandSalaryItem> getAllByStandardId(Long paramLong);
}
