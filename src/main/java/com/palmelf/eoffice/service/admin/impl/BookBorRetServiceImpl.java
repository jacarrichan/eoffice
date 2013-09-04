package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.admin.BookBorRetDao;
import com.palmelf.eoffice.model.admin.BookBorRet;
import com.palmelf.eoffice.service.admin.BookBorRetService;

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
