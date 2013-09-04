package com.palmelf.eoffice.service.document;

import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.document.DocPrivilege;
import com.palmelf.eoffice.model.system.AppUser;

import java.util.List;

public abstract interface DocPrivilegeService extends BaseService<DocPrivilege> {
	public abstract List<DocPrivilege> getAll(DocPrivilege paramDocPrivilege,
			Long paramLong, PagingBean paramPagingBean);

	public abstract List<Integer> getRightsByFolder(AppUser paramAppUser,
			Long paramLong);

	public abstract Integer getRightsByDocument(AppUser paramAppUser,
			Long paramLong);
}
