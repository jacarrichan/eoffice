package com.cyjt.oa.dao.info.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.info.NewsCommentDao;
import com.cyjt.oa.model.info.NewsComment;

public class NewsCommentDaoImpl extends BaseDaoImpl<NewsComment> implements
		NewsCommentDao {
	public NewsCommentDaoImpl() {
		super(NewsComment.class);
	}
}
