package com.cyjt.oa.service.hrm.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.hrm.StandSalaryItemDao;
import com.cyjt.oa.model.hrm.StandSalaryItem;
import com.cyjt.oa.service.hrm.StandSalaryItemService;
import java.util.List;

public class StandSalaryItemServiceImpl extends
		BaseServiceImpl<StandSalaryItem> implements StandSalaryItemService {
	private StandSalaryItemDao dao;

	public StandSalaryItemServiceImpl(StandSalaryItemDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<StandSalaryItem> getAllByStandardId(Long standardId) {
		return this.dao.getAllByStandardId(standardId);
	}
}
