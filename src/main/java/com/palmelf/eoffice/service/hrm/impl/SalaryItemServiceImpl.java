package com.palmelf.eoffice.service.hrm.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.hrm.SalaryItemDao;
import com.palmelf.eoffice.model.hrm.SalaryItem;
import com.palmelf.eoffice.service.hrm.SalaryItemService;

import java.util.List;

public class SalaryItemServiceImpl extends BaseServiceImpl<SalaryItem>
		implements SalaryItemService {
	private SalaryItemDao dao;

	public SalaryItemServiceImpl(SalaryItemDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<SalaryItem> getAllExcludeId(String excludeIds, PagingBean pb) {
		return this.dao.getAllExcludeId(excludeIds, pb);
	}
}
