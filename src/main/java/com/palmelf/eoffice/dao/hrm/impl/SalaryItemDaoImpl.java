package com.palmelf.eoffice.dao.hrm.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.hrm.SalaryItemDao;
import com.palmelf.eoffice.model.hrm.SalaryItem;

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
