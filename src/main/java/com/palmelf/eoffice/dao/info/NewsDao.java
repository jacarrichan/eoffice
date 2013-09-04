package com.palmelf.eoffice.dao.info;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.info.News;

import java.util.List;

public abstract interface NewsDao extends BaseDao<News> {
	public abstract List<News> findByTypeId(Long paramLong,
			PagingBean paramPagingBean);

	public abstract List<News> findBySearch(String paramString,
			PagingBean paramPagingBean);

	public abstract List<News> findImageNews(PagingBean paramPagingBean);
}
