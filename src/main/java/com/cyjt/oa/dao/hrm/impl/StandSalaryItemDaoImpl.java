package com.cyjt.oa.dao.hrm.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.hrm.StandSalaryItemDao;
import com.cyjt.oa.model.hrm.StandSalaryItem;
import java.util.List;

public class StandSalaryItemDaoImpl extends BaseDaoImpl<StandSalaryItem>
		implements StandSalaryItemDao {
	public StandSalaryItemDaoImpl() {
		super(StandSalaryItem.class);
	}

	public List<StandSalaryItem> getAllByStandardId(Long standardId) {
		String hql = "from StandSalaryItem ssi where ssi.standSalary.standardId=?";
		return findByHql(hql, new Object[] { standardId });
	}
}
