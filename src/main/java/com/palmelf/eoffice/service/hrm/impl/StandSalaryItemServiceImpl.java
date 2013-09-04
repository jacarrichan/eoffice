package com.palmelf.eoffice.service.hrm.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.hrm.StandSalaryItemDao;
import com.palmelf.eoffice.model.hrm.StandSalaryItem;
import com.palmelf.eoffice.service.hrm.StandSalaryItemService;

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
