package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.BookTypeDao;
import com.palmelf.eoffice.model.admin.BookType;
import com.palmelf.eoffice.service.admin.BookTypeService;

public class BookTypeServiceImpl extends BaseServiceImpl<BookType> implements
		BookTypeService {
	private BookTypeDao dao;

	public BookTypeServiceImpl(BookTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}
