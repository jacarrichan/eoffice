package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.admin.BookDao;
import com.palmelf.eoffice.model.admin.Book;

import java.util.List;

public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {
	public BookDaoImpl() {
		super(Book.class);
	}

	public List<Book> findByTypeId(Long typeId, PagingBean pb) {
		String hql = "from Book b where b.bookType.typeId=?";
		Object[] params = { typeId };
		return findByHql("from Book b where b.bookType.typeId=?", params, pb);
	}
}
