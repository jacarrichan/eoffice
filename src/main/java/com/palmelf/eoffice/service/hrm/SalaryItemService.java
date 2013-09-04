package com.palmelf.eoffice.service.hrm;

import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.hrm.SalaryItem;

import java.util.List;

public abstract interface SalaryItemService extends BaseService<SalaryItem> {
	public abstract List<SalaryItem> getAllExcludeId(String paramString,
			PagingBean paramPagingBean);
}
