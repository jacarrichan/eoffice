package com.palmelf.eoffice.dao.hrm.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.hrm.StandSalaryItemDao;
import com.palmelf.eoffice.model.hrm.StandSalaryItem;

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
