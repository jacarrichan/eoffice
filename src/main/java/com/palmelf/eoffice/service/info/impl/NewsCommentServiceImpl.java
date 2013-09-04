package com.palmelf.eoffice.service.info.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.info.NewsCommentDao;
import com.palmelf.eoffice.model.info.NewsComment;
import com.palmelf.eoffice.service.info.NewsCommentService;

public class NewsCommentServiceImpl extends BaseServiceImpl<NewsComment>
		implements NewsCommentService {
	private NewsCommentDao dao;

	public NewsCommentServiceImpl(NewsCommentDao dao) {
		super(dao);
		this.dao = dao;
	}
}
