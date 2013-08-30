package com.cyjt.oa.dao.archive;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.archive.ArchDispatch;
import com.cyjt.oa.model.system.AppUser;
import java.util.List;

public abstract interface ArchDispatchDao extends BaseDao<ArchDispatch> {
	public abstract List<ArchDispatch> findByUser(AppUser paramAppUser,
			PagingBean paramPagingBean);

	public abstract List<ArchDispatch> findRecordByArc(Long paramLong);
}
