package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.admin.BookDao;
import com.palmelf.eoffice.model.admin.Book;
import com.palmelf.eoffice.service.admin.BookService;

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
