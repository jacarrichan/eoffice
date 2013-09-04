package com.palmelf.eoffice.dao.communicate;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.communicate.MailBox;

import java.util.List;

public abstract interface MailBoxDao extends BaseDao<MailBox> {
	public abstract Long CountByFolderId(Long paramLong);

	public abstract List<MailBox> findByFolderId(Long paramLong);

	public abstract List<MailBox> findBySearch(String paramString,
			PagingBean paramPagingBean);
}
