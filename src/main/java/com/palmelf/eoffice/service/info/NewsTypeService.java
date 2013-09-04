package com.palmelf.eoffice.service.info;

import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.info.NewsType;

import java.util.List;

public abstract interface NewsTypeService extends BaseService<NewsType> {
	public abstract Short getTop();

	public abstract NewsType findBySn(Short paramShort);

	public abstract List<NewsType> getAllBySn();

	public abstract List<NewsType> getAllBySn(PagingBean paramPagingBean);

	public abstract List<NewsType> findBySearch(NewsType paramNewsType,
			PagingBean paramPagingBean);
}
