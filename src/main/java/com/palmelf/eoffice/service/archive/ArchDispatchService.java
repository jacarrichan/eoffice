package com.palmelf.eoffice.service.archive;

import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.archive.ArchDispatch;
import com.palmelf.eoffice.model.system.AppUser;

import java.util.List;

public abstract interface ArchDispatchService extends BaseService<ArchDispatch> {
	public abstract List<ArchDispatch> findByUser(AppUser paramAppUser,
			PagingBean paramPagingBean);

	public abstract int countArchDispatch(Long paramLong);
}
