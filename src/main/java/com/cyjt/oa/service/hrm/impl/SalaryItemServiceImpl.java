package com.cyjt.oa.service.hrm.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.hrm.SalaryItemDao;
import com.cyjt.oa.model.hrm.SalaryItem;
import com.cyjt.oa.service.hrm.SalaryItemService;
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
