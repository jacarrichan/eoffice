package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.BookSnDao;
import com.palmelf.eoffice.model.admin.BookSn;

import java.util.List;

public class BookSnDaoImpl extends BaseDaoImpl<BookSn> implements BookSnDao {
	public BookSnDaoImpl() {
		super(BookSn.class);
	}

	public List<BookSn> findByBookId(Long bookId) {
		String hql = "from BookSn b where b.book.bookId=?";
		Object[] params = { bookId };
		return findByHql("from BookSn b where b.book.bookId=?", params);
	}

	public List<BookSn> findBorrowByBookId(Long bookId) {
		String hql = "from BookSn b where b.book.bookId=? and b.status=0";
		Object[] params = { bookId };
		return findByHql("from BookSn b where b.book.bookId=? and b.status=0",
				params);
	}

	public List<BookSn> findReturnByBookId(Long bookId) {
		String hql = "from BookSn b where b.book.bookId=? and b.status=1";
		Object[] params = { bookId };
		return findByHql("from BookSn b where b.book.bookId=? and b.status=1",
				params);
	}
}
