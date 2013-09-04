package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.BookSnDao;
import com.palmelf.eoffice.model.admin.BookSn;
import com.palmelf.eoffice.service.admin.BookSnService;

import java.util.List;

public class BookSnServiceImpl extends BaseServiceImpl<BookSn> implements
		BookSnService {
	private BookSnDao dao;

	public BookSnServiceImpl(BookSnDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<BookSn> findByBookId(Long bookId) {
		return this.dao.findByBookId(bookId);
	}

	public List<BookSn> findBorrowByBookId(Long bookId) {
		return this.dao.findBorrowByBookId(bookId);
	}

	public List<BookSn> findReturnByBookId(Long bookId) {
		return this.dao.findReturnByBookId(bookId);
	}
}
