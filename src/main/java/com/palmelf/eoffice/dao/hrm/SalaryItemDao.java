package com.palmelf.eoffice.dao.hrm;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.hrm.SalaryItem;

import java.util.List;

public abstract interface SalaryItemDao extends BaseDao<SalaryItem> {
	public abstract List<SalaryItem> getAllExcludeId(String paramString,
			PagingBean paramPagingBean);
}
