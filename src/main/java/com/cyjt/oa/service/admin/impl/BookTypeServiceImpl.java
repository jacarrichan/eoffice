package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.BookTypeDao;
import com.cyjt.oa.model.admin.BookType;
import com.cyjt.oa.service.admin.BookTypeService;

public class BookTypeServiceImpl extends BaseServiceImpl<BookType> implements
		BookTypeService {
	private BookTypeDao dao;

	public BookTypeServiceImpl(BookTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
