package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.BookTypeDao;
import com.palmelf.eoffice.model.admin.BookType;

public class BookTypeDaoImpl extends BaseDaoImpl<BookType> implements
		BookTypeDao {
	public BookTypeDaoImpl() {
		super(BookType.class);
	}
}
