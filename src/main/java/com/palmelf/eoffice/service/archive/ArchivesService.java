package com.palmelf.eoffice.service.archive;

import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.archive.Archives;
import com.palmelf.eoffice.model.system.AppRole;

import java.util.List;
import java.util.Set;

public abstract interface ArchivesService extends BaseService<Archives> {
	public abstract List<Archives> findByUserOrRole(Long paramLong,
			Set<AppRole> paramSet, PagingBean paramPagingBean);
}
