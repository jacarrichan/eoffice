package com.cyjt.oa.dao.info;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.info.News;
import java.util.List;

public abstract interface NewsDao extends BaseDao<News> {
	public abstract List<News> findByTypeId(Long paramLong,
			PagingBean paramPagingBean);

	public abstract List<News> findBySearch(String paramString,
			PagingBean paramPagingBean);

	public abstract List<News> findImageNews(PagingBean paramPagingBean);
}
