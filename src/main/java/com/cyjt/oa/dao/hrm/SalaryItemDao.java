package com.cyjt.oa.dao.hrm;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.hrm.SalaryItem;
import java.util.List;

public abstract interface SalaryItemDao extends BaseDao<SalaryItem> {
	public abstract List<SalaryItem> getAllExcludeId(String paramString,
			PagingBean paramPagingBean);
}
