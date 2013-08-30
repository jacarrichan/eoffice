package com.cyjt.oa.dao.document;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.document.DocPrivilege;
import com.cyjt.oa.model.system.AppUser;
import java.util.List;

public abstract interface DocPrivilegeDao extends BaseDao<DocPrivilege> {
	public abstract List<DocPrivilege> getAll(DocPrivilege paramDocPrivilege,
			Long paramLong, PagingBean paramPagingBean);

	public abstract List<DocPrivilege> getByPublic(
			DocPrivilege paramDocPrivilege, Long paramLong);

	public abstract List<Integer> getRightsByFolder(AppUser paramAppUser,
			Long paramLong);

	public abstract Integer getRightsByDocument(AppUser paramAppUser,
			Long paramLong);

	public abstract Integer countPrivilege();
}
