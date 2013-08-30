package com.cyjt.oa.dao.communicate;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.communicate.MailBox;
import java.util.List;

public abstract interface MailBoxDao extends BaseDao<MailBox> {
	public abstract Long CountByFolderId(Long paramLong);

	public abstract List<MailBox> findByFolderId(Long paramLong);

	public abstract List<MailBox> findBySearch(String paramString,
			PagingBean paramPagingBean);
}
