package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.BookTypeDao;
import com.cyjt.oa.model.admin.BookType;

public class BookTypeDaoImpl extends BaseDaoImpl<BookType> implements
		BookTypeDao {
	public BookTypeDaoImpl() {
		super(BookType.class);
	}
}
