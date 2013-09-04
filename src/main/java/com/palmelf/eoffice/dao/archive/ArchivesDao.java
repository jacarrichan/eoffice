package com.palmelf.eoffice.dao.archive;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.archive.Archives;
import com.palmelf.eoffice.model.system.AppRole;

import java.util.List;
import java.util.Set;

public abstract interface ArchivesDao extends BaseDao<Archives> {
	public abstract List<Archives> findByUserOrRole(Long paramLong,
			Set<AppRole> paramSet, PagingBean paramPagingBean);
}
