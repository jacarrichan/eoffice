package com.palmelf.eoffice.dao.archive;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.archive.ArchDispatch;
import com.palmelf.eoffice.model.system.AppUser;

import java.util.List;

public abstract interface ArchDispatchDao extends BaseDao<ArchDispatch> {
	public abstract List<ArchDispatch> findByUser(AppUser paramAppUser,
			PagingBean paramPagingBean);

	public abstract List<ArchDispatch> findRecordByArc(Long paramLong);
}
