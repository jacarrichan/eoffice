package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.admin.BookBorRetDao;
import com.cyjt.oa.model.admin.BookBorRet;
import com.cyjt.oa.service.admin.BookBorRetService;
import java.util.List;

public class BookBorRetServiceImpl extends BaseServiceImpl<BookBorRet>
		implements BookBorRetService {
	private BookBorRetDao dao;

	public BookBorRetServiceImpl(BookBorRetDao dao) {
		super(dao);
		this.dao = dao;
	}

	public BookBorRet getByBookSnId(Long bookSnId) {
		return this.dao.getByBookSnId(bookSnId);
	}

	public List getBorrowInfo(PagingBean pb) {
		return this.dao.getBorrowInfo(pb);
	}

	public List getReturnInfo(PagingBean pb) {
		return this.dao.getReturnInfo(pb);
	}

	public Long getBookBorRetId(Long snId) {
		return this.dao.getBookBorRetId(snId);
	}
}
