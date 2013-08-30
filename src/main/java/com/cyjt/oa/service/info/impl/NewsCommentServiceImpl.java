package com.cyjt.oa.service.info.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.info.NewsCommentDao;
import com.cyjt.oa.model.info.NewsComment;
import com.cyjt.oa.service.info.NewsCommentService;

public class NewsCommentServiceImpl extends BaseServiceImpl<NewsComment>
		implements NewsCommentService {
	private NewsCommentDao dao;

	public NewsCommentServiceImpl(NewsCommentDao dao) {
		super(dao);
		this.dao = dao;
	}
}
