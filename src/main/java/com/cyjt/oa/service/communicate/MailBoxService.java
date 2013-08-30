package com.cyjt.oa.service.communicate;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.communicate.MailBox;
import java.util.List;

public abstract interface MailBoxService extends BaseService<MailBox> {
	public abstract Long CountByFolderId(Long paramLong);

	public abstract List<MailBox> findByFolderId(Long paramLong);

	public abstract List<MailBox> findBySearch(String paramString,
			PagingBean paramPagingBean);
}
