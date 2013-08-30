package com.cyjt.oa.dao.hrm.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.hrm.SalaryItemDao;
import com.cyjt.oa.model.hrm.SalaryItem;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class SalaryItemDaoImpl extends BaseDaoImpl<SalaryItem> implements
		SalaryItemDao {
	public SalaryItemDaoImpl() {
		super(SalaryItem.class);
	}

	public List<SalaryItem> getAllExcludeId(String excludeIds, PagingBean pb) {
		String hql = "from SalaryItem ";
		if (StringUtils.isNotEmpty(excludeIds)) {
			hql = hql + "where salaryItemId not in(" + excludeIds + ")";
		}
		return findByHql(hql, null, pb);
	}
}
