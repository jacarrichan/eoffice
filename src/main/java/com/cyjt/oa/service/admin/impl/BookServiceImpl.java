package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.admin.BookDao;
import com.cyjt.oa.model.admin.Book;
import com.cyjt.oa.service.admin.BookService;
import java.util.List;

public class BookServiceImpl extends BaseServiceImpl<Book> implements
		BookService {
	private BookDao dao;

	public BookServiceImpl(BookDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Book> findByTypeId(Long typeId, PagingBean pb) {
		return this.dao.findByTypeId(typeId, pb);
	}
}
