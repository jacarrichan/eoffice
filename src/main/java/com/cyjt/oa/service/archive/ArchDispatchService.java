package com.cyjt.oa.service.archive;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.archive.ArchDispatch;
import com.cyjt.oa.model.system.AppUser;
import java.util.List;

public abstract interface ArchDispatchService extends BaseService<ArchDispatch> {
	public abstract List<ArchDispatch> findByUser(AppUser paramAppUser,
			PagingBean paramPagingBean);

	public abstract int countArchDispatch(Long paramLong);
}
