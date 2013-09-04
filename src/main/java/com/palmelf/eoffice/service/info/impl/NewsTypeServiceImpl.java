package com.palmelf.eoffice.service.info.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.info.NewsTypeDao;
import com.palmelf.eoffice.model.info.NewsType;
import com.palmelf.eoffice.service.info.NewsTypeService;

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
