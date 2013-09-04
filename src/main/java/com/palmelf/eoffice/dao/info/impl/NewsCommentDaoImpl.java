package com.palmelf.eoffice.dao.info.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.info.NewsCommentDao;
import com.palmelf.eoffice.model.info.NewsComment;

public class NewsCommentDaoImpl extends BaseDaoImpl<NewsComment> implements
		NewsCommentDao {
	public NewsCommentDaoImpl() {
		super(NewsComment.class);
	}
}
