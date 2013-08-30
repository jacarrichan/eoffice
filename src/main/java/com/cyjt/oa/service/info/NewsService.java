package com.cyjt.oa.service.info;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.info.News;
import java.util.List;

public abstract interface NewsService extends BaseService<News> {
	public abstract List<News> findByTypeId(Long paramLong,
			PagingBean paramPagingBean);

	public abstract List<News> findBySearch(String paramString,
			PagingBean paramPagingBean);

	public abstract List<News> findImageNews(PagingBean paramPagingBean);
}
