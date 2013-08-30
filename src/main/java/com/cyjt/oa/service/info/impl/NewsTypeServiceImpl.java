package com.cyjt.oa.service.info.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.info.NewsTypeDao;
import com.cyjt.oa.model.info.NewsType;
import com.cyjt.oa.service.info.NewsTypeService;
import java.util.List;

public class NewsTypeServiceImpl extends BaseServiceImpl<NewsType> implements
		NewsTypeService {
	private NewsTypeDao newsTypeDao;

	public NewsTypeServiceImpl(NewsTypeDao dao) {
		super(dao);
		this.newsTypeDao = dao;
	}

	public Short getTop() {
		return this.newsTypeDao.getTop();
	}

	public NewsType findBySn(Short sn) {
		return this.newsTypeDao.findBySn(sn);
	}

	public List<NewsType> getAllBySn() {
		return this.newsTypeDao.getAllBySn();
	}

	public List<NewsType> getAllBySn(PagingBean pb) {
		return this.newsTypeDao.getAllBySn(pb);
	}

	public List<NewsType> findBySearch(NewsType newsType, PagingBean pb) {
		return this.newsTypeDao.findBySearch(newsType, pb);
	}
}
