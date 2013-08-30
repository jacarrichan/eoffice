package com.cyjt.oa.dao.info;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.info.NewsType;
import java.util.List;

public abstract interface NewsTypeDao extends BaseDao<NewsType> {
	public abstract Short getTop();

	public abstract NewsType findBySn(Short paramShort);

	public abstract List<NewsType> getAllBySn();

	public abstract List<NewsType> getAllBySn(PagingBean paramPagingBean);

	public abstract List<NewsType> findBySearch(NewsType paramNewsType,
			PagingBean paramPagingBean);
}
