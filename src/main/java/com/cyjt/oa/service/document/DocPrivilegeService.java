package com.cyjt.oa.service.document;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.document.DocPrivilege;
import com.cyjt.oa.model.system.AppUser;
import java.util.List;

public abstract interface DocPrivilegeService extends BaseService<DocPrivilege> {
	public abstract List<DocPrivilege> getAll(DocPrivilege paramDocPrivilege,
			Long paramLong, PagingBean paramPagingBean);

	public abstract List<Integer> getRightsByFolder(AppUser paramAppUser,
			Long paramLong);

	public abstract Integer getRightsByDocument(AppUser paramAppUser,
			Long paramLong);
}
