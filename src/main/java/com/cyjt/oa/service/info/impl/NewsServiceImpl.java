package com.cyjt.oa.service.info.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.info.NewsDao;
import com.cyjt.oa.model.info.News;
import com.cyjt.oa.service.info.NewsService;
import java.util.List;

public class NewsServiceImpl extends BaseServiceImpl<News> implements
		NewsService {
	private NewsDao newsDao;

	public NewsServiceImpl(NewsDao dao) {
		super(dao);
		this.newsDao = dao;
	}

	public List<News> findByTypeId(Long typeId, PagingBean pb) {
		return this.newsDao.findByTypeId(typeId, pb);
	}

	public List<News> findBySearch(String searchContent, PagingBean pb) {
		return this.newsDao.findBySearch(searchContent, pb);
	}

	public List<News> findImageNews(PagingBean pb) {
		return this.newsDao.findImageNews(pb);
	}
}
