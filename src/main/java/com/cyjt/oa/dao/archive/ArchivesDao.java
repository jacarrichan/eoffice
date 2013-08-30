package com.cyjt.oa.dao.archive;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.archive.Archives;
import com.cyjt.oa.model.system.AppRole;
import java.util.List;
import java.util.Set;

public abstract interface ArchivesDao extends BaseDao<Archives> {
	public abstract List<Archives> findByUserOrRole(Long paramLong,
			Set<AppRole> paramSet, PagingBean paramPagingBean);
}
