package com.cyjt.oa.service.hrm;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.hrm.SalaryItem;
import java.util.List;

public abstract interface SalaryItemService extends BaseService<SalaryItem> {
	public abstract List<SalaryItem> getAllExcludeId(String paramString,
			PagingBean paramPagingBean);
}
